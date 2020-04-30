package acceptance.backend.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class CompanyTests extends TestBaseAdmin {

  @Test
  public void testSendRegisterLinkForCompany() throws Exception {
    long now = System.currentTimeMillis();
    String email = String.format("dk%s@test.test", now);

    appManager.getNavHelper().openCompanyPageWithNotRegisterStatus();
    appManager.getCompanyPageHelper().clickSendRegisterLinkButton();
    appManager.getCompanyPageHelper().fillEmailField(By.id("email"), email);
    appManager.getCompanyPageHelper().clickSendButton();
    appManager.getCompanyPageHelper().closeToastContainer();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//tr[1]//td[8]//button[1]")).getText().contains("Відправити посилання повторно"));
  }

  @Test
  public void testResendRegisterLinkForCompany() throws Exception {
    long now = System.currentTimeMillis();
    String email = String.format("%s@test.test", now);
    appManager.getNavHelper().openCompanyPageWithResendRegisterStatus();
    appManager.getCompanyPageHelper().clickSendRegisterLinkButton();
    Thread.sleep(2000);
    appManager.getCompanyPageHelper().clickSendButton();
    appManager.getCompanyPageHelper().closeToastContainer();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
  }

  @Test
  public void testGoToCardsOfCompanyPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickCardButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Картки"));
  }

  @Test
  public void testGoToCardsOfCompanyPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickCompanyButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Картки"));
  }

  @Test
  public void testGoToTransactionsOfCompanyPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickTransactionsButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Транзакції"));
  }

  @Test
  public void testGoToTransactionsOfCompanyPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickTransactionsButtonFromCompanyProfile();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Транзакції"));
  }

  @Test
  public void testGoToClientsUsersPage() throws Exception {
    appManager.getNavHelper().openCompanyPage();
    appManager.getCompanyPageHelper().waitAndClickUsersButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список користувачів"));
  }

  @Test
  public void testGoToClientsUsersPageFromProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().waitAndClickUsersButtonFromCompanyPage();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список користувачів"));
  }

  @Test
  public void testGoToCompanyProfilePage() throws Exception {
    appManager.getNavHelper().openCompanyPageWithRegisterStatus();
    appManager.getCompanyPageHelper().waitAndClickCompanyProfilePageButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//b[contains(text(),'1')]")).getText().contains("Назва компанії в 1С:"));
  }

  @Test
  public void testModifyCompanyProfile() throws Exception {
    appManager.getNavHelper().openCompanyProfilePage();

    appManager.getCompanyPageHelper().fillCompanyProfileFields("ModNamOfCompany", "test@test.test" + new Random().nextInt(10000), "04212 - Малиновского ул. Н(1-15), Ч(2-10)", "+380987165311");
    appManager.getCompanyPageHelper().clickSubmitAndRedirectButton();
    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Список компаній"));
  }

 @Test(dataProvider = "docsTypes")
  public void testDownloadXlsDocument(String value) throws Exception{
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().goToDownloadPage();
    appManager.getCompanyPageHelper().selectTypeOfDoc(value);
    appManager.getCompanyPageHelper().downloadXLSDocument();
    appManager.getCompanyPageHelper().clickSaveButton();

    assertTrue(appManager.getNavHelper().driver.findElement(By.xpath("//div[contains(@class,'alert-dismissable')]")).getText().contains("Дані успішно збережено"));
  }

  @Test(dataProvider = "docsTypes")
  public void testDownloadPDFDocument(String value) throws Exception{
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().goToDownloadPage();
    appManager.getCompanyPageHelper().selectTypeOfDoc(value);
    appManager.getCompanyPageHelper().downloadPDFDocument();
    appManager.getCompanyPageHelper().clickSaveAndBackButton();

    assertTrue(appManager.getNavHelper().driver.findElement(By.xpath("//h4[contains(@class,'page-title')]")).getText().contains("Профіль"));
    assertTrue(appManager.getNavHelper().driver.findElement(By.xpath("//div[contains(@class,'alert-dismissable')]")).getText().contains("Дані успішно збережено"));
  }

  @DataProvider(name = "docsTypes")
  Object[][] getData(){
    String docsData[][] = {
            {"invoice"},
            {"act-checking"},
            {"appendix-petroleum-products"},
            {"card-invoice"},
            {"acceptance-transfer-act"}
    };
    return (docsData);
  }

  @Test
  public void testDownloadDocAndClickReturnButton() throws Exception{
    appManager.getNavHelper().openCompanyProfilePage();
    appManager.getCompanyPageHelper().goToDownloadPage();
    appManager.getCompanyPageHelper().downloadXLSDocument();
    appManager.getCompanyPageHelper().clickBackButton();

    assertTrue(appManager.getNavHelper().driver.findElement(By.xpath("//h4[contains(@class,'page-title')]")).getText().contains("Список компаній"));
    }
}
