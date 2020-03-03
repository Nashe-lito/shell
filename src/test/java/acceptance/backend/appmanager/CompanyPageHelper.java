package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CompanyPageHelper extends HelperBaseAdm{

  public CompanyPageHelper(ChromeDriver driver) {
    super(driver);
  }

  public void clickSendRegisterLinkButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[8]//button[1]"));
  }

  public void clickSendButton() {
    click(By.xpath("//button[@class='btn btn-success']"));
  }

  public void fillEmailField(By locator, String text) {
    clearKeyAndType(locator, text);
  }

  public void waitAndClickCardButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[1]"));
  }

  public void waitAndClickCompanyButton() {
    waitLoaderAndClick(By.xpath("//div[@class='row p-b-20']//a[1]"));
  }

  public void waitAndClickTransactionsButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[3]"));
  }

  public void waitAndClickTransactionsButtonFromCompanyProfile() {
    waitLoaderAndClick(By.xpath("//a[2]"));
  }

  public void waitAndClickUsersButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[4]"));
  }

  public void waitAndClickUsersButtonFromCompanyPage() {
    waitLoaderAndClick(By.xpath("//a[3]"));
  }

  public void waitAndClickCompanyProfilePageButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[2]"));
  }

  public void clickSubmitAndRedirectButton() {
    click(By.xpath("//button[@id='btn__submit-and-redirect']"));
  }

  public void fillCompanyProfileFields(String modNamOfCompany, String email, String address, String tel) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-success')]")));

    type(By.xpath("//input[@id='name']"), modNamOfCompany);
    type(By.xpath("//input[@id='email']"), email + new Random().nextInt(10000));
    type(By.xpath("//input[@id='postalAddress']"), address);
    type(By.xpath("//input[@id='accountingEmail']"), email + new Random().nextInt(10000));
    type(By.xpath("//input[@id='accountingPhone']"), tel);
  }

  /*public void downloadDocument() {
    driver.findElement(By.xpath("//a[4]")).click();
    WebDriverWait wait = new WebDriverWait(driver, 5);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      driver.findElement(By.id("document")).sendKeys("0a3147406c77ad2aa275d483ec4a4b961b934665_1581947992.pdf");
    }
    driver.findElement(By.id("document")).clear();
    driver.findElement(By.id("document"));
  }*/
}
