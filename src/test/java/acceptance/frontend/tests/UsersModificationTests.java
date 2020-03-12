package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.DriverData;
import acceptance.frontend.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


public class UsersModificationTests extends TestBase {

  @Test
  public void testModificationUser() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
    }
    app.getNavigationHelper().goToUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().fillModifyUserForm("NameMod", "SurnameMod", "SecondName", "manager", "+380987165319", "mod12345678");
    app.getUserHelper().submitFormButton();
    app.getUserHelper().getUsersPageAndRefresh();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());
  }
}

