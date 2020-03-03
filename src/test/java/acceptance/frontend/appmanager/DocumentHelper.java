package acceptance.frontend.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DocumentHelper extends HelperBase{

  public DocumentHelper(WebDriver driver) {
    super(driver);
  }

public void selectStartOfPeriod(){
   // type(By.xpath("//input[@placeholder='Select start of period']"), "Січень 2020");
  click(By.xpath("//input[@placeholder='Select start of period']"));
  click(By.xpath("//div[@class='flatpickr-monthSelect-months']//span[@class='flatpickr-monthSelect-month']"));
}

  public void selectEndOfPeriod(){
    click(By.xpath("//input[@placeholder='Select end of period']"));
    click(By.xpath("//div[@class='flatpickr-calendar animate flatpickr-monthSelect-theme-red open arrowTop']//span[@class='flatpickr-monthSelect-month selected']"));
  }

  public void clickOnSendButton(){
    click(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void clickOnReturnToDocumentsButton(){
    click(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']"));
  }

  public void selectRadioButtonAnotherAmount() {
    click(By.xpath("//div[@class='c-bill-field']//label[2]//span[1]"));
  }

  public void fillAnotherAmountField(String sum){
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/label/div/label[2]/span[3]/input"), sum);
  }

  public void clickAddTMCButton(){
    click(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[2]/span"));
  }

  public void selectAdnFillTMC(String sum){
    click(By.xpath("//select[@class='c-select__native']"));
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div/div/div/div/div/div/div/div/div/span[2]/select"))).selectByVisibleText("Бензин VP А-95-Евро5-Е5");

    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/input"), sum);
  }

}
