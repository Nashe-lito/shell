package acceptance.frontend.appmanager;

import acceptance.frontend.model.ApplyNewCardData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardHelper extends HelperBase {


  public CardHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOkButtonPopUp() {
    click(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void fillApplayForNewCardsForm(ApplyNewCardData applyNewCardData) {
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/label/input"), (applyNewCardData.getNumberOfCards()));
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/label/input"), (applyNewCardData.getFullName()));
    type(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/label/input"), (applyNewCardData.getTel()));
  }

  public  void fillSearchInput(String cardNum){
    type(By.xpath("//form[@class='c-simple-search']//input[@class='c-simple-search__input']"), cardNum);
  }


  public void clickOnSearchButton() {
    click(By.xpath("//form[@class='c-simple-search']//span[@class='c-button__label']"));
  }

  public void clickOnClearSearchButton() {
    click(By.xpath("//form[@class='c-simple-search']//span[@class='c-icon c-icon--close is-actionable']"));
  }

  public void openModifyCardPage(By locatorForOpenPopover, By locatorForModify){
    click(locatorForOpenPopover);
    click(locatorForModify);
  }

  public void clickOnOpenSetOwnerPopUpButton(){
    click(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']"));
  }

  public void selectDriver(){
    click(By.xpath("//*[@id='overlay-root']/div/div[4]/div/div/div[2]/div/div[3]/div[1]/p"));
  }

  public void clickAddDriverButton(){
    click(By.xpath("//div[@class='c-search__actions']//div[@class='c-button c-button--primary']"));
  }

  public void clickDeleteDriverButton(){
    click(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']"));
  }

  public void clickOkButton(){
    click(By.xpath("//div[@class='c-popup__actions']//div[@class='c-button c-button--primary']"));
  }


}
