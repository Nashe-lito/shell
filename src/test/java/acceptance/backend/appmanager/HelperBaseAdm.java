package acceptance.backend.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBaseAdm {
  ChromeDriver driver;

  protected boolean acceptNextAlert = true;

  public HelperBaseAdm(ChromeDriver driver) {
    this.driver = driver;
  }

  protected void click(By locator) {
    driver.findElement(locator).click();
  }

  protected void select(String registerStatus, By locator) {
    click(locator);
    new Select(driver.findElement(locator)).selectByValue(registerStatus);
  }

  protected void type(By locator, String text) {
    driver.findElement(locator).click();
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  protected void clearKeyAndType(By locator, String text) {
    driver.findElement(locator).click();
    driver.findElement(locator).sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
    driver.findElement(locator).sendKeys(text);
  }

  protected void waitLoaderAndClick(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      click(locator);
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
