package acceptance.frontend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void goToUsersPage() {
    click(By.xpath("//div[@class='m-navigation']//div[2]//a[1]"));
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div/div[2]/a"));
  }
}
