package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;

public class SessHelper extends HelperBaseAdm {

  public SessHelper(ChromeDriver driver) {
   super(driver);
  }

  @DataProvider(name = "Authentication")
  public Object[][] dataProviderMethod() {
    return new Object[][]{
            {"root", "111"},
            {"Sereda", "12345678"}
    };
  }

  public void login(String username, String password) {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/admin/sign-in");
    type(By.name("username"),username);
    type(By.name("password"),password);
    click(By.xpath("//*[@id='loginform']/div[4]/div/button"));

    assertTrue(driver.findElement(By.xpath("//h4[@class='page-title']")).getText().contains("Dashboard"));
  }
}
