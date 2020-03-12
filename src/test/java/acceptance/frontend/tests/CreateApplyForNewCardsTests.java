package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.ApplyNewCardData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CreateApplyForNewCardsTests extends TestBase {
  private WebDriver driver;

  @Test
  public void testCreateApplyForNewCards() throws Exception {
    app.getNavigationHelper().openCreateCardsPage();
    app.getCardHelper().fillApplayForNewCardsForm(new ApplyNewCardData("12", "Test Test", "+380987165311"));
    app.getUserHelper().submitFormButton();
    app.getCardHelper().clickOkButtonPopUp();
  }
}
