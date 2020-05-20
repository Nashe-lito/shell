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

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class CheckingAvailabilityOfPagesForManagerTests {
  private WebDriver driver;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    //   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);
    driver.get("https://shell-b2b.test.aurocraft.com/uk/auth");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    String username = "test23701904";
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

  @Test (groups = {"manager"},dependsOnGroups = {"createManager"})
  public void testOpenCompanyProfilePageReturn403Error(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/company");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test (groups = {"manager"},dependsOnGroups = {"createManager"})
  public void testOpenEditCompanyProfilePageReturn403Error(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/company/edit");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test (groups = {"manager"},dependsOnGroups = {"createManager"})
  public void testOpenUserPageReturn403Error(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test (groups = {"manager"},dependsOnGroups = {"createManager"})
  public void testOpenDocumentPageReturn403Error(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/documents");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @Test(enabled = false)//у  менеджера есть доступ. этот момент до конца не согласован
  public void testOpenDriverPageReturn403Error(){
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/drivers");

    assertTrue(driver.findElement(By.xpath("//div[@class='m-access-error']//p[@class='c-text c-text--h1 a-color-dark m-access-error__title']"))
            .getText().contains("У вас недостатньо прав для доступу до цього розділу"));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown(){
    driver.quit();
  }
}
