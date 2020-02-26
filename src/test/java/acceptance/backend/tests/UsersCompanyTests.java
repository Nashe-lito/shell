package acceptance.backend.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UsersCompanyTests extends TestBaseAdmin{
  private WebDriver driver;

  @Test(priority = 0)
  public void testModifyUser() throws Exception{
    appManager.getNavHelper().openUserOfCompanyPage();
    appManager.getUserHelper().waitAndClickEditUserButton();
//   Thread.sleep(3000);
    appManager.getUserHelper().fillUserForm("testUserName", "test@test.test", "Тестович", "Тест", "Тестов", "1qaz@WSX3edc", "+380987165399");
    appManager.getUserHelper().clickSubmitAndRedirectButton();

/*    Thread.sleep(3000);
    assertTrue(driver.findElement(By.xpath("//tr[1]/td[2]/a")).getText().contains(email));*/
  }


  @Test(priority = 1)
  public void testDeleteUser() throws Exception{
    appManager.getNavHelper().openUserPageWithActiveUsers();
    appManager.getUserHelper().waitAndClickDeleteButton();
    appManager.getUserHelper().clickDeleteConfigButton();
    //проверить, что стало на 1 запись меньше
  }

  @Test(priority = 2)
  public void testBlockUser() throws Exception{
    appManager.getNavHelper().openUserPageWithActiveUsers();
    appManager.getUserHelper().waitAndClickEditUserButton();
    appManager.getUserHelper().clickChangeStatusButton();

    appManager.getUserHelper().clickSuccessChangeStatusButton();
// проверить, что пользователь заблокирован
  }


  @Test(priority = 3) // баг
  public void tesUnblockUser() throws Exception{
    appManager.getNavHelper().openUserPageWithBlockUsers();
    appManager.getUserHelper().waitAndClickEditUserButton();
    Thread.sleep(3000);
    appManager.getUserHelper().clickChangeStatusButton();
    appManager.getUserHelper().clickSuccessChangeStatusButton();
// проверить, что пользователь разаблокирован
  }

  @Test(priority = 4)
  public void testSendRestorePassLink() throws Exception{
    appManager.getNavHelper().openUserOfCompanyPage();
    appManager.getUserHelper().clickSendRestorePassLinkButton();
  }

}

