package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchCardTests extends TestBase {

  @Test
  public void testSearchCard() throws Exception{
    app.getNavigationHelper().openCardsPage();
    app.getCardHelper().fillSearchInput("2101054");
    app.getCardHelper().clickOnSearchButton();

 //   Thread.sleep(3000);
 //   assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div[1]/span")).getText().contains("2101054"));
  }

  @Test
  public void testClickOnCloseIconClearSearchField() throws Exception {
    app.getNavigationHelper().openCardsPage();
    app.getCardHelper().fillSearchInput("2101054");
    app.getCardHelper().clickOnClearSearchButton();

 //   assertFalse(driver.findElement(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']")).getText().contains("2101054"));
  }
}
