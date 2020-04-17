package acceptance.backend.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class UsersCompanyTests extends TestBaseAdmin{
  private WebDriver driver;

  @Test
  public void testModifyUser() throws Exception{
    appManager.getNavHelper().openUserOfCompanyPage();
    appManager.getUserHelper().waitAndClickEditUserButton();
    appManager.getUserHelper().fillUserForm("testUserName", "test@test.test", "Тестович", "Тест", "Тестов", "1qaz@WSX3edc", "+380987165399");
    appManager.getUserHelper().clickSubmitAndRedirectButton();
  }

  @Test
  public void testDeleteUser() throws Exception{
    appManager.getNavHelper().openUserPageWithActiveUsers();
    appManager.getUserHelper().waitAndClickDeleteButton();
    appManager.getUserHelper().clickDeleteConfigButton();
  }

  @Test
  public void testBlockUser() throws Exception{
    appManager.getNavHelper().openUserPageWithActiveUsers();
    appManager.getUserHelper().waitAndClickEditUserButton();
    appManager.getUserHelper().clickChangeStatusButton();
    appManager.getUserHelper().clickSuccessChangeStatusButton();
  }

  @Test(enabled = false) // баг
  public void tesUnblockUser() throws Exception{
    appManager.getNavHelper().openUserPageWithBlockUsers();
    appManager.getUserHelper().waitAndClickEditUserButton();
    Thread.sleep(3000);
    appManager.getUserHelper().clickChangeStatusButton();
    appManager.getUserHelper().clickSuccessChangeStatusButton();
  }

  @Test
  public void testSendRestorePassLink() throws Exception{
    appManager.getNavHelper().openUserOfCompanyPage();
    appManager.getUserHelper().clickSendRestorePassLinkButton();
  }
}

