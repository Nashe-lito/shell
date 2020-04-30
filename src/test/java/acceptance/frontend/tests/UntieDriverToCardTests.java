package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UntieDriverToCardTests extends TestBase {

  @Test
  public void testUntieDriverToCardT() throws Exception{
    app.getNavigationHelper().openActiveCardsPage();
    if (app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[2]//div[3]//span[1]")).getText().contains("-")){
      app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div[2]/div/div/p"));
      app.getCardHelper().clickOnOpenSetOwnerPopUpButton();
      Thread.sleep(3000);
      app.getCardHelper().selectDriver();
      app.getCardHelper().clickAddDriverButton();
      app.getNavigationHelper().openActiveCardsPage();
    }
    app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div[2]/div/div/p"));
    app.getCardHelper().clickDeleteDriverButton();
    app.getCardHelper().clickOkButton();

    app.getNavigationHelper().openActiveCardsPage();
    assertTrue(app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[2]//div[3]//span[1]")).getText().contains("-"));
  }
}
