package acceptance.frontend.company.tests;

import acceptance.frontend.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateDriversTests extends TestBase {

  @Test
  public void testCreateDriver() throws Exception {
    app.getNavigationHelper().goToDriversPage();
    app.getDriverHelper().createDriver("Surname", "Name", "Middlename", "test@test.test", "active", "+380987165399",  "AA1234BB", "Ea eos harum eripuit delenit, ex duis omnes eirmod vel. Sed an debitis pericula consulatu. Eu facilis mentitum gloriatur est, blandit accumsan definitiones est cu. Mel audiam nominati sapientem ut, iisque adolescens usu ex. Simul aliquando est eu, p");
    if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id=\"overlay-root\"]/div/div[4]/div/div/div[2]/div/div[1]/span"))){
      app.getDriverHelper().closePopUp();
    }
  }
}
