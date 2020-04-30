package acceptance.frontend.appmanager;

import acceptance.frontend.model.DriverData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class DriverHelper extends HelperBase {

  public DriverHelper(WebDriver driver) {
    super(driver);
  }

  public void goToCreateDriverPage() {
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[1]/div[2]/a"));
  }

  public void fillDriverFieldsForCreateForm(DriverData driverData) {
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[1]/label/input"), driverData.getSurname());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[2]/label/input"), driverData.getName());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]/label/input"), driverData.getMiddlename());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[2]/label/input"), driverData.getEmail());

    driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select"))).selectByValue(driverData.getStatus());

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[1]/div/label/input"), driverData.getTel());

    click(By.xpath("//div[@class='m-layout__main']//div[3]//div[1]//div[1]//div[1]//div[2]//span[1]"));
    type(By.xpath("//div[@class='m-layout__main']//div[3]//div[1]//div[1]//div[1]//div[1]//div[1]//label[1]//input[1]"), driverData.getCarNum());

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/label/textarea"), driverData.getNote());
  }

  public void fillDriverFieldsForModifyForm(DriverData driverData) {
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[1]/label/input"), driverData.getSurname());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[2]/label/input"), driverData.getName());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]/label/input"), driverData.getMiddlename());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[2]/label/input"), driverData.getEmail());

    driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select"))).selectByValue(driverData.getStatus());

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[1]/div/label/input"), driverData.getTel());

    type(By.xpath("//div[@class='m-layout__main']//div[3]//div[1]//div[1]//div[1]//div[1]//div[1]//label[1]//input[1]"), driverData.getCarNum());

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/label/textarea"), driverData.getNote());
  }

  public void clickSubmitButton() {
    click(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void clickOkButton() {
    click(By.xpath("//div[@class='c-button c-button--primary']"));
  }

  public void closePopUp() {
    click(By.xpath("//*[@id=\"overlay-root\"]/div/div[4]/div/div/div[2]/div/div[1]/span"));
  }

  public void goToEditDriverPage(int index) throws InterruptedException {
    scrollDown("scroll(0,  1400)");
    driver.findElements(By.xpath("//span[@class='c-icon c-icon--more']")).get(index).click();
    click(By.xpath("//span/div/div[2]/div/div/p"));
  }

  public void clickOnChangeStatusButtonOnDriversPage() {
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div[2]/div/div[2]/p"));
  }

  public void clickOnDeleteButtonOnDriversPage() {
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div[2]/div/div[3]/p"));
  }

  public void clickOnYesButton() {
    click(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void clickChangeStatusButtonOnModifyPage() {
    click(By.xpath("//div[@class='c-page-header__actions']//div[2]//span[1]"));
  }

  public void clickDeleteButtonOnModifyPage() {
    click(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]"));
  }

  public void createDriver(DriverData driver) throws InterruptedException {
    goToCreateDriverPage();
    fillDriverFieldsForCreateForm(driver);
    scrollDown("scroll(0,  1400)");
    clickSubmitButton();
  }

  public void modifyDriver(int before) throws InterruptedException {
    scrollDown("scroll(0,  1400)");
    goToEditDriverPage(before - 1);
    fillDriverFieldsForModifyForm(new DriverData("SurnameTest", "NameTest", "MiddlenameTest", "mod@test.test", "blocked", "+380987165311", "Mod111", "Ea eos harum" + new Random().nextInt(10000)));
    clickSubmitButton();
    clickOkButton();
  }

  public boolean isThereADriver() {
    return isElementPresent(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
  }

  public int getDriverCount() {
    return driver.findElements(By.xpath("//span[@class='c-icon c-icon--more']")).size();
  }
}
