package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class SessHelper extends HelperBaseAdm {

  public SessHelper(WebDriver driver) {
   super(driver);
  }

/*
  @DataProvider
  public Object[][] dataProviderMethod() {
    return new Object[][]{
            {"root", "111"},
            {"Sereda", "12345678"}
    };
  }

  @Parameters({"username", "password"})
*/

  public void login(String username, String password) {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/sign-in");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.xpath("//*[@id='loginform']/div[4]/div/button"));

    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Dashboard"));
  }
}
