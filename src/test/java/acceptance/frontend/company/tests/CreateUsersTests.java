package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.UserData;
import org.testng.annotations.*;

public class CreateUsersTests extends TestBase {


  @Test
  public void testCreateUsers() throws Exception {
    app.getNavigationHelper().goToUsersPage();
    app.getUserHelper().goToCreateUserPage();
    app.getUserHelper().fillUserForm(new UserData("Name", "Surname", "accountant", "+380987165311", "12345678"));
    app.getUserHelper().submitFormButton();
    app.getUserHelper().getUsersPageAndRefresh();
   // checkUserCreation(new UserData("Surname"));
  }

/*  private void checkUserCreation(UserData userData) {
    assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div[3]/span")).getText().contains(userData.getCreateSurname());
  }*/

}