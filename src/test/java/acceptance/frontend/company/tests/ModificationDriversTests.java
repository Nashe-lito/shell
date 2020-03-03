package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import org.testng.annotations.Test;

public class ModificationDriversTests extends TestBase {

  @Test
  public void testModifyDriver() throws Exception {
    app.getNavigationHelper().openActiveDriversPage();
    if (! app.getDriverHelper().isThereADriver()){
      app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165399", "AA1234BB", "Ea eos");
    }
    app.getNavigationHelper().openActiveDriversPage();
    app.getDriverHelper().goToEditDriverPage();
    app.getDriverHelper().fillDriverFields("SurnameTest", "NameTest", "MiddlenameTest", "mod@test.test", "blocked", "+380987165311","Mod111", "Ea eos harum");
    app.getDriverHelper().clickSubmitButton();
  }
}
