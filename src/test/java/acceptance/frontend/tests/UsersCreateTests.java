package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.DriverData;
import acceptance.frontend.model.UserData;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class UsersCreateTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")));) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(UserData.class);
      List<UserData> users = (List<UserData>) xstream.fromXML(xml);
      return users.stream().map((u) -> new Object[]{u}).collect(Collectors.toList()).iterator();
    }

  }


  @Test(dataProvider = "validUsersFromXml")
  public void testCreateUsers(UserData user) throws Exception {
    app.getNavigationHelper().goToUsersPage();
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().createUser(user);
    if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id=\"overlay-root\"]/div/div[4]/div/div/div[2]/div/div"))) {
      app.getUserHelper().clickOK();
    }
     app.getUserHelper().getUsersPageAndRefresh();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(),before.size() +1);
  }

/*  private void checkUserCreation(UserData userData) {
    assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div[3]/span")).getText().contains(userData.getCreateSurname());
  }*/

}