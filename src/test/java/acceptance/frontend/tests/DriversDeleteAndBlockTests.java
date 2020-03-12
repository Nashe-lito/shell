package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.DriverData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DriversDeleteAndBlockTests extends TestBase {

@Test(priority = 1)
  public void testBlockUserThroughTable() throws Exception {
    app.getNavigationHelper().openActiveDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165366", "AA1234BB", "Ea eos"));
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
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165344","AA1234BB", "Ea eos"));
    }
    app.getNavigationHelper().openActiveDriversPage();
    app.getDriverHelper().goToEditDriverPage(0);
    app.getDriverHelper().clickChangeStatusButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
  }

  @Test(priority = 3)
  public void testUnblockedDriverThroughTable() throws Exception {
    app.getNavigationHelper().openBlockedDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "blocked",  "+380987165311","AA1234BB", "Ea eos"));
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
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos"));
    }
    app.getNavigationHelper().openBlockedDriversPage();
    app.getDriverHelper().goToEditDriverPage(0);
    app.getDriverHelper().clickChangeStatusButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();

  }

  @Test(priority = 5)
  public void testDeleteUserThroughTable() throws Exception {
    app.getNavigationHelper().goToDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos"));
    }
    app.getNavigationHelper().goToDriversPage();
    int before = app.getDriverHelper().getDriverCount();
    app.getDriverHelper().clickOnDeleteButtonOnDriversPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
    int after = app.getDriverHelper().getDriverCount();
    Assert.assertEquals(after, before-1);
  }

  @Test(priority = 6)
  public void testDeleteUserFromModifyPage() throws Exception {

    app.getNavigationHelper().goToDriversPage();
    if (!app.getDriverHelper().isThereADriver()) {
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "blocked", "+380987165399", "AA1234BB", "Ea eos"));
    }
    app.getNavigationHelper().goToDriversPage();
    int before = app.getDriverHelper().getDriverCount();
    app.getDriverHelper().goToEditDriverPage(before -1);
    Thread.sleep(3000);
    app.getDriverHelper().clickDeleteButtonOnModifyPage();
    app.getDriverHelper().clickOnYesButton();
    app.getDriverHelper().clickOnYesButton();
    app.getNavigationHelper().goToDriversPage();
    int after = app.getDriverHelper().getDriverCount();
    Assert.assertEquals(after, before-1);
  }
}
