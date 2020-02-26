package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;

public class UserHelper extends HelperBaseAdm{

  public UserHelper(ChromeDriver driver) {
    super(driver);
  }

  public void fillUserForm(String userName, String email, String lastName, String firstName, String middleName, String password, String tel) {
    type(By.id("username"), userName + new Random().nextInt(10000));
    type(By.id("email"), email + new Random().nextInt(10000));
    type(By.id("lastName"), lastName);
    type(By.id("firstName"), firstName);
    type(By.id("middleName"), middleName);
    type(By.id("password"), password);
    type(By.id("repeatPassword"), password);
    type(By.id("phone"), tel);
    click(By.xpath("//div[@id='page-wrapper']/div/form/div/div/div/div[9]/div/button/div/div/div"));

    driver.findElement(By.xpath("//a[@id='bs-select-1-1']/span")).click();
    new Select(driver.findElement(By.id("role"))).selectByVisibleText("менеджер");

  }

  public void clickSubmitAndRedirectButton() {
    click(By.xpath("//button[@id='btn__submit-and-redirect']"));
  }

  public void waitAndClickEditUserButton() {
    waitLoaderAndClick(By.xpath("//tr[1]/td[9]/div/a"));
  }

  public void clickDeleteConfigButton() {
    click(By.id("btn-delete-confirm"));
  }

  public void waitAndClickDeleteButton() {
    waitLoaderAndClick(By.xpath("//tr[2]//td[9]//div[1]//button[1]"));
  }

  public void clickSuccessChangeStatusButton() {
    click(By.xpath("//button[@class='btn btn-success']"));
  }

  public void clickChangeStatusButton() {
    click(By.xpath("//button[@class='btn waves-effect waves-light btn-success change-status']"));
  }

  public void clickSendRestorePassLinkButton() {
    waitLoaderAndClick(By.xpath("//button[@class='btn btn-xs btn-success send-restore-pass-link']"));
  }
}
