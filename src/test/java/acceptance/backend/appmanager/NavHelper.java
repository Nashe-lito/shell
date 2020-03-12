package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavHelper extends HelperBaseAdm{


  public NavHelper(WebDriver driver) {
    super(driver);
  }

  public void openCompanyPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list");
  }

  public void openUserOfCompanyPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/user/list");
  }

  public void openClientOfCompanyPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/card/list");
  }

  public void openClientsTransactionsPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/transaction/card/list");
  }

  public void openCompanyPageWithNotRegisterStatus() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list?clientId=&clientName=&cardNumber=&managerId=&type=&status=&registerStatus=not-registered");
  }

  public void openCompanyPageWithResendRegisterStatus() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list?clientId=&clientName=&cardNumber=&managerId=&type=&status=&registerStatus=resend-register-link");
  }

  public void openCompanyProfilePage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/profile/6c9f744d-906e-4074-8b93-7d9fdec0338d");
  }

  public void openCompanyPageWithRegisterStatus() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/client/list?clientId=&clientName=&cardNumber=&managerId=&type=&status=&registerStatus=registered");
  }

  public void openUserPageWithActiveUsers() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/user/list?role=&status=active&company=&client1cId=&email=&fullName=");
  }

  public void openUserPageWithBlockUsers() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/clients/user/list?role=&status=blocked&company=&client1cId=&email=&fullName=");
  }

  public void openUsersOfAdminPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/users/user/list");
  }

  public void clickOnPagination(){
    click(By.xpath("//a[@class='page-link'][contains(text(),'2')]"));
  }
}
