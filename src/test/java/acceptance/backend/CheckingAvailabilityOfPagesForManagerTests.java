package acceptance.backend;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class CheckingAvailabilityOfPagesForManagerTests {
  private WebDriver driver;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);

    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/sign-in");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).sendKeys("Sereda");
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

  @Test(enabled = false) //bag
  public void testFiltersOnClientUsersPage() throws InterruptedException {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/user/list");
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список користува"));
    WebDriverWait wait = new WebDriverWait(driver, 7);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      driver.findElement(By.xpath("//div[@class='panel-heading']//span[@data-perform='panel-collapse']")).click();
    }

    driver.findElement(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div/div/div/button/div/div/div")).click();
    driver.findElement(By.id("bs-select-1-1")).click();
    new Select(driver.findElement(By.id("role"))).selectByVisibleText("адмін");

    driver.findElement(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div/div[2]/div/button/div/div/div")).click();
    driver.findElement(By.id("bs-select-2-2")).click();
    new Select(driver.findElement(By.id("status"))).selectByVisibleText("активний");

    driver.findElement(By.id("company")).click();
    driver.findElement(By.id("company")).clear();
    driver.findElement(By.id("company")).sendKeys("E");

    driver.findElement(By.id("client1cId")).click();
    driver.findElement(By.id("client1cId")).clear();
    driver.findElement(By.id("client1cId")).sendKeys("кв-0001332");

    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("dm.bogdanovych@aurocraft.com");

    driver.findElement(By.id("fullName")).click();
    driver.findElement(By.id("fullName")).clear();
    driver.findElement(By.id("fullName")).sendKeys("test@test.test");

    driver.findElement(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-primary')]")).click();
    Thread.sleep(3000);

    assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("dm.bogdanovych@aurocraft.com"));
  }

  @Test
  public void testFiltersOnClientCardsPage() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/card/list");
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Картки"));
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

    driver.findElement(By.id("status")).click();
    new Select(driver.findElement(By.id("status"))).selectByValue("1");

    driver.findElement(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-primary')]")).click();
    Thread.sleep(3000);

    assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("2101039"));
  }

  @Test
  public void testClientsTransactionsPage() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/transaction/card/list");
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Транзакції"));
    WebDriverWait wait = new WebDriverWait(driver, 7);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible){
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

    driver.findElement(By.id("azsName")).click();
    driver.findElement(By.id("azsName")).clear();
    driver.findElement(By.id("azsName")).sendKeys("АЗС WOG №47 м. Рівне, вул. Дубенська, 163 а");

    driver.findElement(By.id("dateFrom")).click();
    driver.findElement(By.id("dateFrom")).clear();
    driver.findElement(By.id("dateFrom")).sendKeys("01-01-2020");

    driver.findElement(By.id("dateTo")).click();
    driver.findElement(By.id("dateTo")).clear();
    driver.findElement(By.id("dateTo")).sendKeys("20-02-2020");

    driver.findElement(By.id("type")).click();
    new Select(driver.findElement(By.id("type"))).selectByValue("write-off");

    driver.findElement(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div[4]/div/div/div/button/div/div/div")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-1-0']/span[2]")).click();
    Thread.sleep(5000);

    driver.findElement(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div[4]/div[2]/div/div/button/div/div/div")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-2-0']/span[2]")).click();

    driver.findElement(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-primary')]")).click();
    Thread.sleep(3000);

    assertTrue(driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText().contains("Бензин Mustang 92"));
    assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-info']//h2")).getText().contains("Сума транзакцій за період"));
  }

  @Test
  public void testFeedbackPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/feedback/list");
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Зворотній зв'язок"));
   assertTrue(driver.findElement(By.xpath("//tr[1]//td[2]")).isDisplayed());
  }

  @Test
  public void testOpenUsersPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/users/user/list");
    assertTrue(driver.findElement(By.xpath("//div/h2[2]")).getText().contains("403"));
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.findElement(By.xpath("//ul[@class='nav navbar-top-links navbar-right pull-right']//a[@class='dropdown-toggle']")).click();
    driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-user']//li//a")).click();
    driver.quit();
  }

}
