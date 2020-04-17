package acceptance.backend.appmanager;

import acceptance.backend.model.UsersOfAdminData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class UsersOfAdminHelper extends HelperBaseAdm {
  public UsersOfAdminHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOnCreateButton(){
    click(By.xpath("//a[contains(@class,'btn waves-effect waves-light btn-success')]"));
  }

  public void fillUserForm(UsersOfAdminData usersOfAdminData) throws InterruptedException {
    attach(By.xpath("//input[@class='dropify dropify-cropper image-element__avatar']"), usersOfAdminData.getPhoto());

    scrollDown("scroll(0, 250)");

    click(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]"));

    type(By.id("manager1CId"), usersOfAdminData.getManagerId());
    type(By.id("name"), usersOfAdminData.getName());
    type(By.id("username"), usersOfAdminData.getLogin());
    type(By.id("email"), usersOfAdminData.getEmail());
    type(By.id("password"), usersOfAdminData.getPassword());
    type(By.id("repeatPassword"), usersOfAdminData.getPassword());
    type(By.id("phone"), usersOfAdminData.getPhone());

    click(By.xpath("//div[@id='page-wrapper']/div/form/div/div[2]/div/div[8]/div/button/div/div/div"));
    click(By.xpath("//a[@id='bs-select-1-0']/span"));
    click(By.xpath("//div[@id='page-wrapper']/div/form/div/div[2]/div/div[9]/div/button/div/div/div"));
    click(By.xpath("//a[@id='bs-select-2-0']/span"));
  }

  public void clickOnBackButton() {
    click(By.xpath("//a[contains(@class,'btn waves-effect waves-light btn-default')]"));
  }
  public void clickOnSubmitAndRedirectButton() {
    click(By.id("btn__submit-and-redirect"));
  }

  public void clickOnEditButton(int index){
    driver.findElements(By.xpath("//a[@class='btn btn-xs btn-info']")).get(index).click();
  }

  public void clickOnDeleteButton(int index){
    driver.findElements(By.xpath("//button[@class='btn btn-xs btn-danger delete_link']")).get(index).click();
  }

  public void clickOnConfirmButton(){
    click(By.xpath("//button[@id='btn-delete-confirm']"));
  }

  public int getUserOfAdminCount() {
    return driver.findElements(By.xpath("//span[@class='tablesaw-cell-content']")).size();
  }


  public List<UsersOfAdminData> getUsersOfAdminListForMod() {
    List<UsersOfAdminData> users = new ArrayList<UsersOfAdminData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("tbody"));
    for (WebElement element : elements){
      String id = element.findElement(By.tagName("tr")).getAttribute("data-key");
      UsersOfAdminData user = new UsersOfAdminData(id,null, null, null, null, null, null, null);
      users.add(user);
    }
    return users;
  }
  public List<UsersOfAdminData> getUsersOfAdminListForDel() {
    List<UsersOfAdminData> users = new ArrayList<UsersOfAdminData>();
  List<WebElement> elements = driver.findElements(By.cssSelector("span.tablesaw-cell-content"));
    for (WebElement element : elements){
      String email = element.getTagName();
      UsersOfAdminData user = new UsersOfAdminData(null, null, null, null, email, null, null);
      users.add(user);
    }
    return users;
  }
}
