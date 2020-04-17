package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.UserData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


public class UsersModificationTests extends TestBase {

  @Test
  public void testModificationUser() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    Thread.sleep(2000);
    if (app.getNavigationHelper().isElementPresent(By.xpath("//li[@class='c-pagination__item']//a[@class='c-pagination__link']"))) {
      app.getNavigationHelper().clickOnPagination();
    }
    if (!app.getUserHelper().isThereAUser()) {
      app.getUserHelper().createUser(new UserData("Name", "Surname", "accountant", "test@test.test" + new Random()
              .nextInt(10000), "+380987165311", "test"+ new Random()
              .nextInt(10000), "1qaz@WSX3edc"));
      app.getNavigationHelper().goToUsersPage();
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().openPopoverOnUsersPage(before.size() - 1);
    app.getUserHelper().clickOnLinkModifyUserPage();
    app.getUserHelper().fillModifyUserForm("NameMod", "SurnameMod", "SecondName", "manager", "+380987165319", "mod12345678");
    app.getUserHelper().submitFormButton();
    app.getUserHelper().getUsersPageAndRefresh();
    if (app.getNavigationHelper().isElementPresent(By.xpath("//li[@class='c-pagination__item']//a[@class='c-pagination__link']"))) {
      app.getNavigationHelper().clickOnPagination();
    }
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());
  }
}

