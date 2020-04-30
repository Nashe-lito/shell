package acceptance.backend.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CardTests {

  private WebDriver driver;

  @BeforeClass
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().window().fullscreen();
    driver.get("https://shell-b2b.test.aurocraft.com/admin");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("root");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("111");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Dashboard"));
  }

  @Test(enabled = false)
  public void testEditCard() throws Exception{
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/card/list?clientId=&clientName=&cardNumber=&status=1");
    WebDriverWait wait = new WebDriverWait(driver, 5);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible){
      driver.findElement(By.xpath("//tr[24]//td[5]//a[1]")).click();
    }
    Thread.sleep(3000);

    driver.findElement(By.id("totalLimits_day")).click();
    driver.findElement(By.id("totalLimits_day")).clear();
    driver.findElement(By.id("totalLimits_day")).sendKeys("10000");

    driver.findElement(By.id("totalLimits_week")).click();
    driver.findElement(By.id("totalLimits_week")).clear();
    driver.findElement(By.id("totalLimits_week")).sendKeys("200000");

    driver.findElement(By.id("totalLimits_month")).click();
    driver.findElement(By.id("totalLimits_month")).clear();
    driver.findElement(By.id("totalLimits_month")).sendKeys("3000000");

    driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default']")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-1-0']")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-1-1']")).click();
    driver.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-default']")).click();

    driver.findElement(By.id("startUseTime_hour")).click();
    new Select(driver.findElement(By.id("startUseTime_hour"))).selectByVisibleText("01");
    driver.findElement(By.id("startUseTime_minute")).click();
    new Select(driver.findElement(By.id("startUseTime_minute"))).selectByVisibleText("02");

    driver.findElement(By.id("endUseTime_hour")).click();
    new Select(driver.findElement(By.id("endUseTime_hour"))).selectByVisibleText("22");
    driver.findElement(By.id("endUseTime_minute")).click();
    new Select(driver.findElement(By.id("endUseTime_minute"))).selectByVisibleText("57");


    driver.findElement(By.xpath("//div[@id='fuelLimits']//button[@class='btn btn-success waves-effect waves-light collection-add']")).click();
    driver.findElement(By.xpath("//div[@class='dropdown bootstrap-select col-sm-4 disabled form-control bs3 dropup']//div[@class='filter-option-inner-inner']")).click();
    driver.findElement(By.xpath("//*[@id='bs-select-4-1']/span[2]")).click();

    // driver.findElement(By.xpath("//body[@class='fix-sidebar']//div[@class='dynamic__collection']//div//div[@class='collection-row']//li[2]//a[1]")).click();

    driver.findElement(By.id("fuelLimits_2_dayLimit")).click();
    driver.findElement(By.id("fuelLimits_2_dayLimit")).clear();
    driver.findElement(By.id("fuelLimits_2_dayLimit")).sendKeys("10");

    driver.findElement(By.id("fuelLimits_2_weekLimit")).click();
    driver.findElement(By.id("fuelLimits_2_weekLimit")).clear();
    driver.findElement(By.id("fuelLimits_2_weekLimit")).sendKeys("20");

    driver.findElement(By.id("fuelLimits_2_monthLimit")).click();
    driver.findElement(By.id("fuelLimits_2_monthLimit")).clear();
    driver.findElement(By.id("fuelLimits_2_monthLimit")).sendKeys("30");

    driver.findElement(By.xpath("//div[@id='fuelLimits']/div/div/div/div/button/i")).click();

    driver.findElement(By.xpath("//div[@id='goodsLimits']//button[@class='btn btn-success waves-effect waves-light collection-add']")).click();
    driver.findElement(By.xpath("//div[@class='dropdown bootstrap-select col-sm-4 disabled form-control bs3 dropup']//div[@class='filter-option-inner-inner']")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Strong 150')]")).click();

    driver.findElement(By.id("goodsLimits_0_dayLimit")).click();
    driver.findElement(By.id("goodsLimits_0_dayLimit")).clear();
    driver.findElement(By.id("goodsLimits_0_dayLimit")).sendKeys("10");

    driver.findElement(By.id("goodsLimits_0_weekLimit")).click();
    driver.findElement(By.id("goodsLimits_0_weekLimit")).clear();
    driver.findElement(By.id("goodsLimits_0_weekLimit")).sendKeys("20");

    driver.findElement(By.id("goodsLimits_0_monthLimit")).click();
    driver.findElement(By.id("goodsLimits_0_monthLimit")).clear();
    driver.findElement(By.id("goodsLimits_0_monthLimit")).sendKeys("30");

    driver.findElement(By.xpath("//div[@id='servicesLimits']//button[@class='btn btn-success waves-effect waves-light collection-add']")).click();
    driver.findElement(By.xpath("//div[@class='dropdown bootstrap-select col-sm-4 disabled form-control bs3 dropup']//div[@class='filter-option-inner-inner']")).click();
    driver.findElement(By.xpath("//span[@class='text'][contains(text(),'Автомийка Бізнес-A')]")).click();

    driver.findElement(By.id("servicesLimits_0_dayLimit")).click();
    driver.findElement(By.id("servicesLimits_0_dayLimit")).clear();
    driver.findElement(By.id("servicesLimits_0_dayLimit")).sendKeys("10");

    driver.findElement(By.id("servicesLimits_0_weekLimit")).click();
    driver.findElement(By.id("servicesLimits_0_weekLimit")).clear();
    driver.findElement(By.id("servicesLimits_0_weekLimit")).sendKeys("20");

    driver.findElement(By.id("servicesLimits_0_monthLimit")).click();
    driver.findElement(By.id("servicesLimits_0_monthLimit")).clear();
    driver.findElement(By.id("servicesLimits_0_monthLimit")).sendKeys("30");

    driver.findElement(By.id("btn__submit-and-redirect")).click();

    assertTrue(driver.findElement(By.xpath("//i[@class='fa fa-circle text-purple m-r-5']")).getText().contains("на модерації"));
  } //у разных карточек разные данные

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
  }
}
