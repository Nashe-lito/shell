package acceptance.backend.tests;

import acceptance.backend.model.UsersOfAdminData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class UsersOfAdminModifyTests extends TestBaseAdmin{
  @Test
  public void testModifyUser() throws Exception {
    File photo = new File("src/test/resources/7.jpg");
    long now = System.currentTimeMillis();
    String login = String.format("user%s", now);
    String email = String.format("mod%s@test.test", now);
    appManager.getNavHelper().openUsersOfAdminPage();
    appManager.getUsersOfAdminHelper().scrollDown("scroll(0,  1400)");
    if (appManager.getNavHelper().isElementPresent(By.xpath("//a[@class='page-link'][contains(text(),'2')]"))) {
      appManager.getNavHelper().clickOnPagination();
    }
    List<UsersOfAdminData> before = appManager.getUsersOfAdminHelper().getUsersOfAdminListForMod();
    UsersOfAdminData user = new UsersOfAdminData(before.get(before.size() - 1).getId(), photo, "NC-" + new Random()
            .nextInt(1000), "Test Test Mod", login, email, "12345679", "+380987165341");
    appManager.getUsersOfAdminHelper().clickOnEditButton(before.size() - 1);
    appManager.getUsersOfAdminHelper().fillUserForm(user);
    appManager.getUsersOfAdminHelper().clickOnSubmitAndRedirectButton();
    List<UsersOfAdminData> after = appManager.getUsersOfAdminHelper().getUsersOfAdminListForMod();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

        /*Comparator<? super UsersOfAdminData> buId = (g1, g2)->Integer.compare(g1.getId(), g2.getId()) ; // метод не подходит, т.к. в текущей реализации id = Strind
    before.sort(buId);
    after.sort(buId);
    Assert.assertEquals(before, after);*/
  }
}
