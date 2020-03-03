package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import org.testng.annotations.Test;

public class DeleteAndBlockDriversTests extends TestBase {

  @Test(priority = 1)
  public void testBlockUserThroughTable() throws Exception {
    app.getNavigationHelper().openActiveDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().openActiveDriversPage();
    app.getDriverHelper().clickOnChangeStatusButtonOnDriversPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
  }

  @Test(priority = 2)
  public void testBlockDriverFromModifyPage() throws Exception {
    app.getNavigationHelper().openActiveDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().openActiveDriversPage();
    app.getDriverHelper().goToEditDriverPage();
    app.getDriverHelper().clickChangeStatusButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
  }

  @Test(priority = 3)
  public void testUnblockedDriverThroughTable() throws Exception {
    app.getNavigationHelper().openBlockedDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().openBlockedDriversPage();
    app.getDriverHelper().clickOnChangeStatusButtonOnDriversPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
  }

  @Test(priority = 4)
  public void testUnblockDriverFromModifyPage() throws Exception {
    app.getNavigationHelper().openBlockedDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().openBlockedDriversPage();
    app.getDriverHelper().goToEditDriverPage();
    app.getDriverHelper().clickChangeStatusButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();

  }

  @Test(priority = 5)
  public void testDeleteUserThroughTable() throws Exception {
    app.getNavigationHelper().goToDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().goToDriversPage();
    app.getDriverHelper().clickOnDeleteButtonOnDriversPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
  }

  @Test(priority = 6)
  public void testDeleteUserFromModifyPage() throws Exception {
    app.getNavigationHelper().goToDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().goToDriversPage();
    app.getDriverHelper().goToEditDriverPage();
    app.getDriverHelper().clickDeleteButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();

  }
}
