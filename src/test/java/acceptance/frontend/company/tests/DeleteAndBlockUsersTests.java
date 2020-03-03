package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class DeleteAndBlockUsersTests extends TestBase {

  @Test(priority = 1)
  public void testBlockUserThroughTable() throws Exception{
    app.getNavigationHelper().openActiveUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
    }
    app.getNavigationHelper().openActiveUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLockLinkOnUsersPage();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
    //  assertTrue(driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[2]//div[2]//span[1]")).getText().contains("Розблокувати користувача"));
  }


  @Test(priority = 2)
  public void testBlockUserFromModifyPage() throws Exception {
    app.getNavigationHelper().openActiveUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
    }
    app.getNavigationHelper().openActiveUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnLockUserButton();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();

 //   assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Розблокувати користувача"));
  }

  @Test(priority = 3)
  public void testUnblockedUserThroughTable() throws Exception{
    app.getNavigationHelper().openBlockedUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
      app.getNavigationHelper().openActiveUsersPage();
      app.getUserHelper().lockUser();
    }
    app.getNavigationHelper().openBlockedUsersPage();
      app.getUserHelper().openPopoverOnUsersPage();
      app.getUserHelper().clickOnUnlockLinkOnUsersPage();
      app.getUserHelper().confirmAction();
      app.getUserHelper().clickOK();

//    assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Заблокована"));
  }

  @Test(priority = 4)
  public void testUnblockUserFromModifyPage() throws Exception {
    app.getNavigationHelper().openBlockedUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
      app.getNavigationHelper().openActiveUsersPage();
      app.getUserHelper().lockUser();
    }
    app.getNavigationHelper().openBlockedUsersPage();
      app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnUnlockUserButton();
    app.getUserHelper().confirmAction();
    app.getUserHelper().clickOK();
 //   assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Заблокувати користувача"));
  }

  @Test(priority = 5)
  public void testDeleteUserThroughTable() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
    }
    app.getUserHelper().getUsersPageAndRefresh();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickDeleteLinkUsersPage();
    app.getUserHelper().confirmDeteleUser();
   // app.getUserHelper().clickOK();
  }

  @Test(priority = 6)
  public void testDeleteUserFromModifyPage() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser("Name", "Surname", "accountant", "+380987165311", "12345678");
    }
    app.getUserHelper().getUsersPageAndRefresh();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnDeleteUserButton();
    app.getUserHelper().confirmDeteleUser();
  }
}
