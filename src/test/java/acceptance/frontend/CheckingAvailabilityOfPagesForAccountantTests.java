package acceptance.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class CheckingAvailabilityOfPagesForAccountantTests {
  private WebDriver driver;

  @BeforeClass(alwaysRun = true)

  public void setUp() throws Exception {
    driver = new ChromeDriver();

    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);
    driver.get("https://shell-b2b.test.aurocraft.com/uk/auth");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    String username = "test23703584";
    String password = "12345678";

    driver.findElement(By.xpath("//input[@class='c-input']")).click();
    driver.findElement(By.xpath("//input[@class='c-input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-input']")).sendKeys(username);
    driver.findElement(By.xpath("//input[@class='c-password__input']")).click();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).sendKeys(password);
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();

    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='m-layout__sidebar']//div[1]//a[2]//p[1]")));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testOpenCompanyProfilePageReturn403Error() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/company");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testOpenEditCompanyProfilePageReturn403Error() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/company/edit");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testOpenUserPageReturn403Error() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test(enabled = false)//у бухгалтера есть доступ. этот момент до конца не согласован
  public void testOpenDriverPageReturn403Error() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/drivers");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testSendRequestAct() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/act");
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div/div/span/div/label[1]/div/input")).click();
    driver.findElement(By.xpath("//div[@class='flatpickr-monthSelect-months']//span[@class='flatpickr-monthSelect-month']")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div/div/span/div/label[2]/div/input")).click();
    driver.findElement(By.xpath("//div[@class='flatpickr-calendar animate flatpickr-monthSelect-theme-red open arrowTop']//span[@class='flatpickr-monthSelect-month selected']")).click();
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']"))).click();

  //  assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__content']//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Документи"));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testInvoiceCustomRequestWithCreditBargaining() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/invoice/custom");
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']"))).click();

    assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__content']//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Документи"));
  }

  @Test(groups = {"accountant"}, dependsOnGroups = "createAccountant")
  public void testInvoiceCustomRequestWithAnotherAmount() throws Exception {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/invoice/custom");
    driver.findElement(By.xpath("//div[@class='c-bill-field']//label[2]//span[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/label/div/label[2]/span[3]/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/label/div/label[2]/span[3]/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/label/div/label[2]/span[3]/input")).sendKeys("150");
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-button c-button--alt']//span[@class='c-button__label']"))).click();

    assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__content']//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Документи"));
  }

/*   @Test //подумать над решением
  public void testInvoiceCalculationRequest() throws Exception {
     driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents/invoice/calculation");
     driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[2]/span")).click();
     driver.findElement(By.xpath("//select[@class='c-select__native']")).click();
     new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div/div/div/div/div/div/div/div/div/span[2]/select"))).selectByVisibleText("Бензин VP А-95-Евро5-Е5");
     driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/input")).click();
     driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/input")).clear();
     driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div/div/div/div/div[1]/div/div/div[2]/div/input")).sendKeys("150");
     driver.findElement(By.xpath("//button[@class='c-button c-button--primary']//span[@class='c-button__label']")).click();
     WebDriverWait wait = new WebDriverWait(driver, 10);
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='c-popup__actions']//div[@class='c-button c-button--alt']//span[@class='c-button__label']"))).click();

     assertTrue(driver.findElement(By.xpath("//div[@class='c-page-header__content']//p[@class='c-text c-text--h1 a-color-dark c-page-header__title']")).getText().contains("Документи"));
   }*/

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
