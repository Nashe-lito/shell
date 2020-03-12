package acceptance.backend.tests;

import acceptance.backend.model.UsersOfAdminData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UsersOfAdminDeleteTests extends TestBaseAdmin{
  @Test
  public void testDeleteUser() throws Exception {
    appManager.getNavHelper().openUsersOfAdminPage();
    appManager.getUsersOfAdminHelper().scrollDown("scroll(0,  1400)");
    if (appManager.getNavHelper().isElementPresent(By.xpath("//a[@class='page-link'][contains(text(),'2')]"))) {
      appManager.getNavHelper().clickOnPagination();
    }
    List<UsersOfAdminData> before = appManager.getUsersOfAdminHelper().getUsersOfAdminListForDel();
    appManager.getUsersOfAdminHelper().clickOnDeleteButton(before.size() - 1);
    appManager.getUsersOfAdminHelper().clickOnConfirmButton();
    List<UsersOfAdminData> after = appManager.getUsersOfAdminHelper().getUsersOfAdminListForDel();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
