package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class UsersDeleteAndBlockTests extends TestBase {

  @Test
  public void testBlockUserThroughTable() throws Exception {
    app.getNavigationHelper().openActiveUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
    }
    app.getNavigationHelper().openActiveUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLockLinkOnUsersPage();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }


  @Test
  public void testBlockUserFromModifyPage() throws Exception {
    app.getNavigationHelper().openActiveUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
    }
    app.getNavigationHelper().openActiveUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnLockUserButton();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
    app.getNavigationHelper().openActiveUsersPage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

  @Test
  public void testUnblockedUserThroughTable() throws Exception {
    app.getNavigationHelper().openBlockedUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
      app.getNavigationHelper().openActiveUsersPage();
      app.getUserHelper().lockUser();
    }
    app.getNavigationHelper().openBlockedUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnUnlockLinkOnUsersPage();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

  @Test
  public void testUnblockUserFromModifyPage() throws Exception {
    app.getNavigationHelper().openBlockedUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
      app.getNavigationHelper().openActiveUsersPage();
      app.getUserHelper().lockUser();
    }
    app.getNavigationHelper().openBlockedUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnUnlockUserButton();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
    app.getNavigationHelper().openBlockedUsersPage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

  @Test
  public void testDeleteUserThroughTable() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
    }
    app.getUserHelper().getUsersPageAndRefresh();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickDeleteLinkUsersPage();
    app.getUserHelper().confirmDeteleUser();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }


  @Test
  public void testDeleteUserFromModifyPage() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
    }
    app.getUserHelper().getUsersPageAndRefresh();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnDeleteUserButton();
    app.getUserHelper().confirmDeteleUser();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
