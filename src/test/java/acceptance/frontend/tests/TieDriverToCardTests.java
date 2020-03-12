package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import org.testng.annotations.Test;

public class TieDriverToCardTests extends TestBase {

  @Test
  public void testTieDriverToCard() throws Exception {
    app.getNavigationHelper().openActiveCardsPage();
    app.getCardHelper().openModifyCardPage();
    app.getCardHelper().clickOnOpenSetOwnerPopUpButton();
    Thread.sleep(5000);
    app.getCardHelper(). selectDriver();
    app.getCardHelper().clickAddDriverButton();
  }
}
