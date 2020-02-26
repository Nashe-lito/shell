package acceptance.frontend.cards;

import acceptance.frontend.base.TestBase;
import acceptance.frontend.model.ApplyNewCardData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateApplyForNewCardsTests extends TestBase {
  private WebDriver driver;

  @Test
  public void testCreateApplyForNewCards() throws Exception {
    openCreateCardsPage();
    fillApplayForNewCardsForm(new ApplyNewCardData("12", "Test Test", "+380987165311"));
    app.getUserHelper().submitFormButton();
    clickOkButtonPopUp();

    //   assertTrue(driver.findElement(By.xpath("//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Картки на пальне"));

  }

  private void clickOkButtonPopUp() {
    driver.findElement(By.xpath("//div[@class='c-button c-button--primary']//span[@class='c-button__label']")).click();
  }

  private void fillApplayForNewCardsForm(ApplyNewCardData applyNewCardData) {
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[1]/label/input")).sendKeys(applyNewCardData.getNumberOfCards());

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/label/input")).sendKeys(applyNewCardData.getFullName());

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/label/input")).sendKeys(applyNewCardData.getTel());
  }

  private void openCreateCardsPage() {
    driver.navigate().to( "https://shell-b2b.test.aurocraft.com/uk/cards/create");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

}
