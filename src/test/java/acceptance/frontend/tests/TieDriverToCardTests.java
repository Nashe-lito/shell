package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TieDriverToCardTests extends TestBase {

  @Test
  public void testTieDriverToCard() throws Exception {
    app.getNavigationHelper().openActiveCardsPage();
    if (app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[2]//div[3]//span[1]")).getText().contains("-")){
    app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[5]/span/div/div[2]/div/div/p"));
    }else if (app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[3]//div[3]//span[1]")).getText().contains("-")){
      app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[3]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[3]/div[5]/span/div/div[2]/div/div/p"));
    }else if (app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[4]//div[3]//span[1]")).getText().contains("-")) {
      app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[4]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[4]/div[5]/span/div/div[2]/div/div/p"));
    }else if (app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[5]//div[3]//span[1]")).getText().contains("-")) {
      app.getCardHelper().openModifyCardPage(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[5]/div[5]/span/div/div/span"), By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[4]/div[5]/span/div/div[2]/div/div/p"));
    }
    app.getCardHelper().clickOnOpenSetOwnerPopUpButton();
  /*  WebElement wait = (new WebDriverWait(app.getNavigationHelper().driver, 30))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='overlay-root']/div/div[4]/div/div/div[2]/div/div[3]/div[1]/p")));*/
   Thread.sleep(3000);
    app.getCardHelper().selectDriver();
    app.getCardHelper().clickAddDriverButton();

    assertTrue(app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']")).isDisplayed());
    assertTrue(app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']")).isDisplayed());
  }

  @Test
  public void testTieAnotherDriverToCard() throws Exception{
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
    app.getCardHelper().clickOnOpenSetOwnerPopUpButton();
    Thread.sleep(3000);
    app.getCardHelper().selectDriver();
    app.getCardHelper().clickAddDriverButton();

    assertTrue(app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']")).isDisplayed());
    assertTrue(app.getNavigationHelper().driver.findElement(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']")).isDisplayed());
  }
}
