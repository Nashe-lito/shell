package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.UserData;
import org.testng.annotations.Test;


public class ModificationUsersTests extends TestBase {

  @Test
  public void testModificationUser() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (! app.getUserHelper().isThereAUser()){
    app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
  }
  app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().fillModifyUserForm("NameMod", "SurnameMod", "SecondName", "manager", "+380987165319", "mod12345678");
    app.getUserHelper().submitFormButton();
    app.getUserHelper().getUsersPageAndRefresh();

   // assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div[3]/span")).getText().contains("SurnameMod"));
  }

}

