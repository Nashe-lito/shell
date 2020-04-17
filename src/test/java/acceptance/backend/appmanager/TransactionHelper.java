package acceptance.backend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionHelper extends HelperBaseAdm{

  public TransactionHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOnCardButton(){
    click(By.xpath("//tr[1]//td[11]//a[1]"));
  }

  public void clickOnCompanyButton(){
    click(By.xpath("//tr[1]//td[11]//a[2]"));
  }
}
