package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.DriverData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DriversModificationTests extends TestBase {

  @Test
  public void testModifyDriver() throws Exception {
    app.getNavigationHelper().goToDriversPage();
    if (! app.getDriverHelper().isThereADriver()){
      app.getDriverHelper().createDriver(new DriverData("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165399",   "AA1234BB", "Ea"));
    }
    app.getNavigationHelper().goToDriversPage();

    int before = app.getDriverHelper().getDriverCount();
    app.getDriverHelper().modifyDriver(before);
    app.getNavigationHelper().goToDriversPage();
    int after = app.getDriverHelper().getDriverCount();
    Assert.assertEquals(after, before);
  }


}
