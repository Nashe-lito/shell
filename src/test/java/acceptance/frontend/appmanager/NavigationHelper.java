package acceptance.frontend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void goToUsersPage() {
    click(By.xpath("//div[@class='m-navigation']//div[2]//a[1]"));
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div/div[2]/a"));
  }

  public void openActiveUsersPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users?status=active");
  }

  public void openBlockedUsersPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users?status=blocked");
  }

  public void goToDriversPage(){
    click(By.xpath("//div[@class='m-navigation']//div[2]//a[1]"));
    click(By.xpath("//div[@class='c-company-grid']//div[2]//div[2]//a[1]//span[1]"));
  }

  public void openDriversPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/drivers");
  }

  public void openActiveDriversPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/drivers?status=active");
  }

  public void openBlockedDriversPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/drivers?status=blocked");
  }

  public void openCreateCardsPage() {
    driver.navigate().to( "https://shell-b2b.test.aurocraft.com/uk/cards/create");
   // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  public void openCardsPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/cards");
  }

  public void openActiveCardsPage() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/cards?status=active");
  }

  public void openActRequestPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/act");
  }

  public void openInvoiceCustomPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/invoice/custom");
  }

  public void openInvoiceCalculationPage(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/invoice/calculation");
  }
}
