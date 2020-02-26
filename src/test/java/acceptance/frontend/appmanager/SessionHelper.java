package acceptance.frontend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver driver) {
    super(driver);
  }

  public void login(String authUsername, String authPassword) {
    driver.get("https://shell-b2b.test.aurocraft.com/uk/auth");
  //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    type(By.xpath("//input[@class='c-input']"), authUsername);
    type(By.xpath("//input[@class='c-password__input']"), authPassword);
    click(By.xpath("//button[@class='c-button c-button--primary']"));
    waitAfterLogin(By.xpath("//div[@class='m-layout__sidebar']//div[1]//a[2]//p[1]"));
  }
}

