package acceptance.backend;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class CheckingAvailabilityOfPagesForAccountantTests {
  private WebDriver driver;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);

    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/sign-in");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).sendKeys("qvasilcuk_1");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("12345678");
    driver.findElement(By.xpath("//*[@id='loginform']/div[4]/div/button")).click();
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Dashboard"));
  }

  @Test
  public void testOpenCompanyPage() throws InterruptedException {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list");
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
    WebDriverWait wait = new WebDriverWait(driver, 7);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      driver.findElement(By.xpath("//div[@class='panel-heading']//span[@data-perform='panel-collapse']")).click();
    }
    driver.findElement(By.id("clientId")).click();
    driver.findElement(By.id("clientId")).clear();
    driver.findElement(By.id("clientId")).sendKeys("кв-0001332");

    driver.findElement(By.id("clientName")).click();
    driver.findElement(By.id("clientName")).clear();
    driver.findElement(By.id("clientName")).sendKeys("нек-сус");

    driver.findElement(By.id("cardNumber")).click();
    driver.findElement(By.id("cardNumber")).clear();
    driver.findElement(By.id("cardNumber")).sendKeys("2101039");

    driver.findElement(By.id("type")).click();
    new Select(driver.findElement(By.id("type"))).selectByValue("1");

    driver.findElement(By.id("status")).click();
    new Select(driver.findElement(By.id("status"))).selectByValue("0");

    driver.findElement(By.id("registerStatus")).click();
    new Select(driver.findElement(By.id("registerStatus"))).selectByValue("registered");

    driver.findElement(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-primary')]")).click();
    Thread.sleep(3000);

    assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("КВ-0001332"));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin");
    driver.findElement(By.xpath("//ul[@class='nav navbar-top-links navbar-right pull-right']//a[@class='dropdown-toggle']")).click();
    driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-user']//li//a")).click();
    driver.quit();
  }
}
