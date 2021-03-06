package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.DriverData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DriversCreateTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validDriversFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/drivers.xml")));) {
    /*list.add(new Object[]{new DriverData("Surname", "Name", "Middlename", "test1@test.test", "active", "+380987165391",   "AA1234B1", "Ea eosq1")});
    list.add(new Object[]{new DriverData("Surname", "Name", "Middlename", "test2@test.test", "active", "+380987165393",   "AA1234B2", "Ea eosq2")});
    list.add(new Object[]{new DriverData("Surname", "Name", "Middlename", "test3@test.test", "active", "+380987165395",   "AA1234B3", "Ea eosq3")});*/

      // BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/drivers.csv")));
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        // String[] split = line.split(";");
        // list.add(new Object[] {new DriverData("Surname", "Name", "Middlename", split[0], "active", split[1], split[2],   split[3])});
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(DriverData.class);
      List<DriverData> drivers = (List<DriverData>) xstream.fromXML(xml);
      return drivers.stream().map((d) -> new Object[]{d}).collect(Collectors.toList()).iterator();
    }

    // return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validDriversFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/drivers.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<DriverData> drivers = gson.fromJson(json, new TypeToken<List<DriverData>>() {
      }.getType());
      return drivers.stream().map((d) -> new Object[]{d}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validDriversFromXml")
  public void testCreateDriver(DriverData driver) throws Exception {
    app.getNavigationHelper().openDriversPage();
    int before = app.getDriverHelper().getDriverCount();
    app.getDriverHelper().createDriver(driver);
    if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id=\"overlay-root\"]/div/div[4]/div/div/div[2]/div/div[1]/span"))) {
      app.getDriverHelper().closePopUp();
    }
    app.getNavigationHelper().openDriversPage();
    int after = app.getDriverHelper().getDriverCount();
    Assert.assertEquals(after, before + 1);
  }
}
