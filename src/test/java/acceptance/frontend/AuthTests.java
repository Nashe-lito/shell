package acceptance.frontend;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AuthTests {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeMethod
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
  //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);
  }


  @Test(enabled = false)
  public void testAuth() throws Exception {
    driver.get("https://shell-b2b.test.aurocraft.com/uk/auth");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    driver.findElement(By.xpath("//input[@class='c-input']")).click();
    driver.findElement(By.xpath("//input[@class='c-input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-input']")).sendKeys("johndou");
    driver.findElement(By.xpath("//input[@class='c-password__input']")).click();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).sendKeys("111");
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();
    driver.findElement(By.xpath("//div[@class='m-navigation__item']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}