package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class DeleteAndBlockUsersTests extends TestBase {

  @Test(priority = 1)
  public void testBlockUserThroughTable() throws Exception{
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLockLinkOnUsersPage();
    app.getUserHelper().confirmAction();

    //  assertTrue(driver.findElement(By.xpath("//div[@class='c-table c-table--with-more']//div[2]//div[2]//span[1]")).getText().contains("Розблокувати користувача"));
  }


  @Test(priority = 2)
  public void testBlockUserFromModifyPage() throws Exception {

    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().goToActiveUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnLockUserButton();
    app.getUserHelper().confirmAction();

 //   assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Розблокувати користувача"));
  }

  @Test(priority = 3)
  public void testUnblockedUserThroughTable() throws Exception{
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().goToLockUsersPage();
    if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div[2]/p"))) {
      app.getUserHelper().openPopoverOnUsersPage();
      app.getUserHelper().clickOnUnlockLinkOnUsersPage();
      app.getUserHelper().confirmAction();
    }

//    assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Заблокована"));
  }

  @Test(priority = 4)
  public void testUnblockUserFromModifyPage() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().goToLockUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnUnlockUserButton();
    app.getUserHelper().confirmAction();

 //   assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__actions']//div[1]//span[1]")).getText().contains("Заблокувати користувача"));
  }

  @Test(priority = 5)
  public void testDeleteUserThroughTable() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickDeleteLinkUsersPage();
    app.getUserHelper().confirmDeteleUser();
  }

  @Test(priority = 6)
  public void testDeleteUserFromModifyPage() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().openPopoverOnUsersPage();
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().clickOnDeleteUserButton();
    app.getUserHelper().confirmDeteleUser();
  }
}
