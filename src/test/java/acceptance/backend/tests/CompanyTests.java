package acceptance.backend.tests;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import org.openqa.selenium.*;

public class CompanyTests extends TestBaseAdmin {

ChromeDriver driver;

  @Test (priority = 0)
  public void testSendRegisterLinkForCompany() throws Exception {
    appManager.getNavHelper().openCompanyPageWithNotRegisterStatus();
    appManager.getCompanyPageHelper().clickSendRegisterLinkButton();
    appManager.getCompanyPageHelper().fillEmailField(By.id("email"), "dariakhomenko@aurocraft.com");
    appManager.getCompanyPageHelper().clickSendButton();
    //   assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
  }

  @Test (priority = 1)
  public void testResendRegisterLinkForCompany() throws Exception {
    appManager.getNavHelper().openCompanyPageWithResendRegisterStatus();
    appManager.getCompanyPageHelper().clickSendRegisterLinkButton();
  //  appManager.getCompanyPageHelper().fillEmailField(By.id("email"), "dariakhomenko@aurocraft.com");
    appManager.getCompanyPageHelper().clickSendButton();
 //   assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
  }

  @Test (priority = 2)
  public void testGoToCardsOfCompanyPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickCardButton();
//    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Картки"));
  }

  @Test (priority = 3)
  public void testGoToCardsOfCompanyPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickCompanyButton();
//    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Картки"));
  }

  @Test (priority = 4)
  public void testGoToTransactionsOfCompanyPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickTransactionsButton();
    //   assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Транзакції"));
  }

  @Test (priority = 5)
  public void testGoToTransactionsOfCompanyPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickTransactionsButtonFromCompanyProfile();
//    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Транзакції"));
  }

  @Test (priority = 6)
  public void testGoToClientsUsersPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickUsersButton();
    //   assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список користувачів"));
  }

  @Test (priority = 7)
  public void testGoToClientsUsersPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickUsersButtonFromCompanyPage();
    //   assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список користувачів"));
  }

  @Test (priority = 8)
  public void testGoToCompanyProfilePage() throws Exception {
    appManager.getNavHelper().openCompanyPageWithRegisterStatus();
    appManager.getCompanyPageHelper().waitAndClickCompanyProfilePageButton();
//    assertTrue(driver.findElement(By.xpath("//b[contains(text(),'1')]")).getText().contains("Назва компанії в 1С:"));
  }

  @Test (priority = 9)
  public void testModifyCompanyProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();

    appManager.getCompanyPageHelper().fillCompanyProfileFields("ModNamOfCompany", "test@test.test", "04212 - Малиновского ул. Н(1-15), Ч(2-10)", "+380987165311");
    appManager.getCompanyPageHelper().clickSubmitAndRedirectButton();
//    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));

  }
 /* @Test(priority = 10)
  public void testDownloadXLSDocument()throws Exception{
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().downloadDocument();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }



  @Test(priority = 11)
  public void testDownloadPDFDocument()throws Exception{
    appManager.getNavHelper().openCompanyProfilePage();

  }*/

}
