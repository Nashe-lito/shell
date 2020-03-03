package acceptance.frontend.cards.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.ApplyNewCardData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateApplyForNewCardsTests extends TestBase {
  private WebDriver driver;

  @Test
  public void testCreateApplyForNewCards() throws Exception {
    app.getNavigationHelper().openCreateCardsPage();
    app.getCardHelper().fillApplayForNewCardsForm(new ApplyNewCardData("12", "Test Test", "+380987165311"));
    app.getUserHelper().submitFormButton();
    app.getCardHelper().clickOkButtonPopUp();

    //   assertTrue(driver.findElement(By.xpath("//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Картки на пальне"));

  }





}
