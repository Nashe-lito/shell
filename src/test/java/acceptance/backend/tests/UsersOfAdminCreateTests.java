package acceptance.backend.tests;

import acceptance.backend.model.UsersOfAdminData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

public class UsersOfAdminCreateTests extends TestBaseAdmin {

  @Test
  public void testCreateUser() throws Exception {
    File photo = new File("src/test/resources/5.jpg");

    appManager.getNavHelper().openUsersOfAdminPage();
    appManager.getUsersOfAdminHelper().scrollDown("scroll(0,  1400)");
    if (appManager.getNavHelper().isElementPresent(By.xpath("//a[@class='page-link'][contains(text(),'2')]"))) {
      appManager.getNavHelper().clickOnPagination();
    }
    int before = appManager.getUsersOfAdminHelper().getUserOfAdminCount();
    appManager.getUsersOfAdminHelper().clickOnCreateButton();
    appManager.getUsersOfAdminHelper().fillUserForm(new UsersOfAdminData(photo, "КВ-" + new Random()
            .nextInt(1000), "Test Test Test", "login" + new Random().nextInt(1000), "test@test.test" + new Random()
            .nextInt(1000), "12345678", "+380987165344"));
    appManager.getUsersOfAdminHelper().clickOnSubmitAndRedirectButton();
    int after = appManager.getUsersOfAdminHelper().getUserOfAdminCount();
    Assert.assertEquals(after, before + 1);
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/5.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

  }
}
