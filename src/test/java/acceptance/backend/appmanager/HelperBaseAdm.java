package acceptance.backend.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class HelperBaseAdm {
  public WebDriver driver;
  public boolean acceptNextAlert = true;

  public HelperBaseAdm(WebDriver driver) {
    this.driver = driver;
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void select(String registerStatus, By locator) {
    click(locator);
    new Select(driver.findElement(locator)).selectByValue(registerStatus);
  }


  public void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = driver.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  public void clearKeyAndType(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = driver.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        driver.findElement(locator).sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        driver.findElement(locator).sendKeys(text);
      }
    }
  }

  public void waitLoaderAndClick(By locator, By preloader) {
    WebDriverWait wait = new WebDriverWait(driver, 7);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(preloader));
    if (invisible) {
      click(locator);
    }
  }

  public void attach(By locator, File file) {

    driver.findElement(locator).sendKeys(file.getAbsolutePath());
  }

  public void scrollDown(String parameter) throws InterruptedException {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript(parameter);
    Thread.sleep(3000);
  }

  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public String closeAlertAndGetItsText() {
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
