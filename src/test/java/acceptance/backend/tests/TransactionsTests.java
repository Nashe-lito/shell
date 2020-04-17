package acceptance.backend.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TransactionsTests extends TestBaseAdmin{
  private WebDriver driver;

  @Test
  public void testGoToCardPage() throws Exception{
    appManager.getNavHelper().openClientsTransactionsPage();
    appManager.getTransactionHelper().clickOnCardButton();

    assertTrue(appManager.getTransactionHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Редагування картки"));
  }

  @Test
  public void testGoProfilePage() throws Exception{
    appManager.getNavHelper().openClientsTransactionsPage();
    appManager.getTransactionHelper().clickOnCompanyButton();

    assertTrue(appManager.getTransactionHelper().driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Профіль"));
  }
}
