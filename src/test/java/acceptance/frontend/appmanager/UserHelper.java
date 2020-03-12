package acceptance.frontend.appmanager;

import acceptance.frontend.model.DriverData;
import acceptance.frontend.model.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver driver) {
    super(driver);
  }

  public void getUsersPageAndRefresh() {
    driver.get("https://shell-b2b.test.aurocraft.com/uk/users");
    driver.navigate().refresh();
  }

  public void fillUserForm(UserData user) {
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input"), user.getCreateName());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input"), user.getCreateSurname());

    select(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[2]/label/span[2]/select"), user.getCreateRole());

  //  String email = "test@test.test" + new Random().nextInt(10000);
    type(By.xpath("//input[@type='email']"), user.getCreateEmail() + new Random().nextInt(10000));

    type(By.xpath("//input[@type='tel']"), user.getCreateTel());

  //  String username = "test" + new Random().nextInt(10000);
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input"), user.getCreateUsername() + new Random().nextInt(10000));

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input"), user.getCreatePassword());
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input"), user.getCreatePassword());
  }

  private void select(By locator, String role) {
    driver.findElement(locator).click();
    new Select(driver.findElement(locator)).selectByValue(role);
  }

  public void submitFormButton() {
    click(By.xpath("//button[@class='c-button c-button--primary']"));
  }

  public void goToCreateUserPage() {
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[1]/div[2]/a"));
  }

  public void confirmAction() {
    click(By.xpath("//div[@class='c-button c-button--primary']"));
    click(By.xpath("//*[@id='overlay-root']/div/div[4]/div/div/div[2]/div/div"));
  }

  public void clickOnLockLinkOnUsersPage() {
    click(By.xpath("//span/div/div[2]/div/div[2]/p"));
  }

  public void openPopoverOnUsersPage(int index) throws InterruptedException {
    scrollDown("scroll(0,  1400)");
    driver.findElements(By.xpath("//span[@class='c-icon c-icon--more']")).get(index).click();
  }

  public void clickOnLockUserButton() {
    click(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]"));
  }

  public void clickOnLinkModifyUserPage() {
    click(By.xpath("//span/div/div[2]/div/div[1]/p"));
  }

  public void clickOnUnlockLinkOnUsersPage() {
    click(By.xpath("//span/div/div[2]/div/div[2]/p"));
  }

  public void confirmDeteleUser() {
    click(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']"));
    click(By.xpath("//div[@class='c-button c-button--primary']"));
  }

  public void clickDeleteLinkUsersPage() {
    click(By.xpath("//span/div/div[2]/div/div[3]/p"));
  }

  public void clickOnUnlockUserButton() {
    click(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]"));
  }

  public void clickOnDeleteUserButton() {
    click(By.xpath("//div[@class='c-page-header__actions']//div[2]"));
  }

/*  public void submitUserModification() {
    click(By.xpath("//button[@class='c-button c-button--primary']"));
  }*/

 public void fillModifyUserForm(String firstNameMod, String surnameMod, String secondName, String role, String telMod, String passwordMode) {
    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/label/input"), firstNameMod);

    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/label/input"), surnameMod);

    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[1]/label/input"), secondName);

    select(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div[2]/label/span[2]/select"), role);

    String email = "modtest@test.test" + new Random().nextInt(10000);
    type(By.xpath("//input[@type='email']"), email);

    type(By.xpath("//input[@type='tel']"), telMod);

    String username = "modtest" + new Random().nextInt(10000);
    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[4]/div[1]/label/input"),username);

    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[5]/div[1]/label/div/input"), passwordMode);

    type(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div/div/div[2]/div/div[1]/div/div[5]/div[2]/label/div/input"), passwordMode);
  }

  public void clickOK() {
    click(By.xpath("//div[@class='c-button c-button--primary']"));
  }

  public void createUser(UserData user) throws InterruptedException{
  //String createName, String createSurname, String createRole, String createTel, String createPassword) {
    goToCreateUserPage();
    fillUserForm(user);
            //new UserData("Name", "Surname", "accountant", "test@test.test", "+380987165311", "test","12345678"));
    submitFormButton();
  }

  public boolean isThereAUser() {
    return isElementPresent(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[6]/span/div/div/span"));
  }

  public void lockUser() throws InterruptedException {
    scrollDown("scroll(0,  1400)");
    openPopoverOnUsersPage(0);
    clickOnLockLinkOnUsersPage();
    confirmAction();
    clickOK();
  }

  public List<UserData> getUserList() {
   List<UserData> users = new ArrayList<UserData>();
   List<WebElement> elements = driver.findElements(By.cssSelector("span.c-tooltip"));
   for (WebElement element : elements){
     String surname = element.getTagName();
     UserData user = new UserData(null, surname, null, null, null, null, null);
     users.add(user);

   }
   return users;
  }
}
