package acceptance.frontend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DriverHelper extends HelperBase {

  public DriverHelper(WebDriver driver) {
    super(driver);
  }

  public void goToCreateDriverPage() {
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[1]/div[2]/a"));
  }

  public void fillDriverFields(String surname, String name, String middlename, String email, String status, String tel, String carNum, String note){
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[1]/label/input"), surname);
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[1]/div[2]/label/input"), name);
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[1]/label/input"), middlename);
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[2]/label/input"), email);

    driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div[3]/div/label/span[2]/select"))).selectByValue(status);

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[1]/div/label/input"), tel);
   // click(By.xpath("//div[@class='c-form-layout__main']//div[2]//div[1]//div[1]//div[1]//div[2]//span[1]"));
   // type(By.xpath("//div[@class='c-form__col']//div[2]//label[1]//input[1]"), tel2);

    click(By.xpath("//div[@class='m-layout__main']//div[3]//div[1]//div[1]//div[1]//div[2]//span[1]"));
    type(By.xpath("//div[@class='m-layout__main']//div[3]//div[1]//div[1]//div[1]//div[1]//div[1]//label[1]//input[1]"), carNum);

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/label/textarea"), note);
  }

  public void clickSubmitButton(){
    click(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }
  public void clickOkButton(){
    click(By.xpath("//div[@class='c-button c-button--primary']"));
  }

  public void closePopUp(){
    click(By.xpath("//*[@id=\"overlay-root\"]/div/div[4]/div/div/div[2]/div/div[1]/span"));
  }

  public void goToEditDriverPage(){
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div[2]/div/div/p"));
  }

  public void clickOnChangeStatusButtonOnDriversPage(){
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div[2]/div/div[2]/p"));
  }

  public void clickOnDeleteButtonOnDriversPage(){
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
    click(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div[2]/div/div[3]/p"));
  }

  public void clickOnYesButton(){
    click(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void clickChangeStatusButtonOnModifyPage(){
    click(By.xpath("//div[@class='c-page-header__actions']//div[2]//span[1]"));
  }

  public void clickDeleteButtonOnModifyPage(){
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[1]/div[2]/div[1]/span"));
  }

  public void createDriver(String surname, String name, String middlename, String email, String status, String tel, String carNum, String note) {
    goToCreateDriverPage();
    fillDriverFields(surname, name, middlename, email, status, tel, carNum,  note);
    clickSubmitButton();
  }

  public boolean isThereADriver() {
    return isElementPresent(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[7]/span/div/div/span"));
  }
}
