package acceptance.backend.appmanager;

import acceptance.backend.model.FiltersData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class FilterHelper extends HelperBaseAdm {

  public StringBuffer verificationErrors = new StringBuffer();
  private boolean acceptNextAlert = true;

  public FilterHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOnFilterButton() throws InterruptedException {
    click(By.xpath("//button[contains(@class,'btn waves-effect waves-light btn-primary')]"));
    Thread.sleep(3000);
  }

  public void selectRegistration(String registerStatus) {
    select(registerStatus, By.id("registerStatus"));
  }

  public void selectStatus(String status) {
    select(status, By.id("status"));
  }

  public void selectType(String type) {
    select(type, By.id("type"));
  }

  public void fillFielsManagerId(FiltersData filtersData) {
    type(By.id("managerId"), filtersData.getManagerId());
  }

  public void fillFieldsManager(String manager) {
    select(manager, By.id("manager"));
  }

  public void fillFieldcardNumber(String cardNumber) {
    type(By.id("cardNumber"), cardNumber);
  }

  public void fillFieldClientName(String clientName) {
    type(By.id("clientName"), clientName);
  }

  public void fillFieldClientId(String clientId) {
    type(By.id("clientId"), clientId);
  }

  public void waitAndClickFilterContainer() {
    waitLoaderAndClick(By.xpath("//div[@class='panel-heading']//span[@data-perform='panel-collapse']"), By.id("preloader"));
  }

  public void fillFieldFullName(String fullName) {
    type(By.id("fullName"), fullName);
  }

  public void fillFieldEmail(String email) {
    type(By.id("email"), email);
  }

  public void fillFieldClient1cId(String client1cId) {
    type(By.id("client1cId"), client1cId);
  }

  public void fillFieldCompany(String company) {
    type(By.id("company"), company);
  }

  public void selectUsersStatus(String usersStatus) {
    click(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div/div[2]/div/button/div/div/div"));
    click(By.xpath("//a[@id='bs-select-2-2']/span"));
    new Select(driver.findElement(By.id("status"))).selectByVisibleText(usersStatus);
  }

  public void selectRole(String userRole) {
    click(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div/div/div/button/div/div/div"));
    click(By.id("bs-select-1-1"));
    new Select(driver.findElement(By.id("role"))).selectByVisibleText(userRole);
  }

  public void selectGam() {
    click(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div[4]/div[2]/div/div/button/div/div/div"));
    click(By.xpath("//a[@id='bs-select-2-0']/span[2]"));
  }

  public void selectGamGroup() throws InterruptedException {
    click(By.xpath("//div[@id='filter-container']/div/div[2]/div/form/div[4]/div/div/div/button/div/div/div"));
    click(By.xpath("//a[@id='bs-select-1-0']/span[2]"));
    Thread.sleep(5000);
  }

  public void fillFieldDateTo(String dateTo) {
    type(By.id("dateTo"), dateTo);
  }

  public void fillFieldDateFrom(String dateFrom) {
    type(By.id("dateFrom"), dateFrom);
  }

  public void fillFieldAzsName(String azsName) {
    type(By.id("azsName"), azsName);
  }


}
