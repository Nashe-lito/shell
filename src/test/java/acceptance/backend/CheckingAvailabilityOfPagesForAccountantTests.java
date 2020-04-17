package acceptance.backend;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
  public void testOpenCompanyPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list");
    assertTrue(driver.findElement(By.xpath("//div/h2[2]")).getText().contains("403"));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin");
    driver.findElement(By.xpath("//ul[@class='nav navbar-top-links navbar-right pull-right']//a[@class='dropdown-toggle']")).click();
    driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-user']//li//a")).click();
    driver.quit();
  }
}
