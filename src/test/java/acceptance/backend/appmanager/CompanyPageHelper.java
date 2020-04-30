package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Random;

public class CompanyPageHelper extends HelperBaseAdm{

  public CompanyPageHelper(WebDriver driver) {
    super(driver);
  }

  public void clickSendRegisterLinkButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[8]//button[1]"), By.id("preloader"));
  }

  public void clickSaveButton(){
    click(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-success')]"));
  }
  public void clickSaveAndBackButton(){
    click(By.xpath("//button[@id='btn__submit-and-redirect']"));
  }

  public void clickBackButton(){
    click(By.xpath("//a[contains(@class,'btn waves-effect waves-light btn-default')]"));
  }

  public void closeToastContainer() {
    click(By.cssSelector("div.toast-message"));
  }

  public void clickSendButton() {
    click(By.xpath("//button[@class='btn btn-success']"));
  }

  public void fillEmailField(By locator, String text) {
    clearKeyAndType(locator, text);
  }

  public void waitAndClickCardButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[1]"), By.id("preloader"));
  }

  public void waitAndClickCompanyButton() {
    waitLoaderAndClick(By.xpath("//div[@class='row p-b-20']//a[1]"), By.id("preloader"));
  }

  public void waitAndClickTransactionsButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[3]"), By.id("preloader"));
  }

  public void waitAndClickTransactionsButtonFromCompanyProfile() {
    waitLoaderAndClick(By.xpath("//a[2]"), By.id("preloader"));
  }

  public void waitAndClickUsersButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[4]"), By.id("preloader"));
  }

  public void waitAndClickUsersButtonFromCompanyPage() {
    waitLoaderAndClick(By.xpath("//a[3]"), By.id("preloader"));
  }

  public void waitAndClickCompanyProfilePageButton() {
    waitLoaderAndClick(By.xpath("//tr[1]//td[10]//a[2]"), By.id("preloader"));
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

  public void goToDownloadPage(){
    click(By.xpath("//a[4]"));
  }

  public void downloadXLSDocument() throws InterruptedException {
    File doc = new File("src/test/resources/d6c136f052651438f393322443b98b86180b01a9_1583139496.xls");

    WebDriverWait wait = new WebDriverWait(driver, 5);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      driver.findElement(By.id("document")).sendKeys(doc.getAbsolutePath());
    }
  }

  public void downloadPDFDocument() throws InterruptedException {
    File doc = new File("src/test/resources/1234567890q<>\"'wertyuioasdfghjklzxcvbnm !#$%&'_+-_=_^_`{__@aurocraftcomQWERTYUIOPASDFGHJKLZXCVBNMйцукенгшщзфывапролдячсмитьбЙЦУКЕНГШЩЗ ФЫВАПРОЛД ЯЧСМИТЬБЮ ЇЄ.pdf");

    WebDriverWait wait = new WebDriverWait(driver, 5);
    boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
    if (invisible) {
      driver.findElement(By.id("document")).sendKeys(doc.getAbsolutePath());
    }
  }

  public void selectTypeOfDoc(String typeOfDoc){
    select(typeOfDoc, By.id("type"));
  }
}
