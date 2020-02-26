package acceptance.frontend.cards;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SearchCardTests {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    Dimension d = new Dimension(1600, 877);
    driver.manage().window().setSize(d);

    driver.get("https://shell-b2b.test.aurocraft.com/uk/auth");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//input[@class='c-input']")).click();
    driver.findElement(By.xpath("//input[@class='c-input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-input']")).sendKeys("johndou");
    driver.findElement(By.xpath("//input[@class='c-password__input']")).click();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).sendKeys("111");
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='m-layout__sidebar']//div[1]//a[2]//p[1]")));
  }

  @Test
  public void testSearchCard() throws Exception{
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/cards");

    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).click();
    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).clear();
    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).sendKeys("2101054");

    driver.findElement(By.xpath("//form[@class='c-simple-search']//span[@class='c-button__label']")).click();

    Thread.sleep(3000);
    assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div[1]/span")).getText().contains("2101054"));
  }

  @Test
  public void testClickOnCloseIconClearSearchField() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/cards");

    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).click();
    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).clear();
    driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).sendKeys("2101054");

    driver.findElement(By.xpath("//form[@class='c-simple-search']//span[@class='c-icon c-icon--close is-actionable']")).click();

    assertFalse(driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).getText().contains("2101054"));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
