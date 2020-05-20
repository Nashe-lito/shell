package acceptance.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateAccountantAndManagerTests {
  private WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/auth");
    driver.findElement(By.xpath("//input[@class='c-input']")).click();
    driver.findElement(By.xpath("//input[@class='c-input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-input']")).sendKeys("johndou");
    driver.findElement(By.xpath("//input[@class='c-password__input']")).click();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).clear();
    driver.findElement(By.xpath("//input[@class='c-password__input']")).sendKeys("111");
    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='m-layout__sidebar']//div[1]//a[2]//p[1]")));
  }

  @Test(groups = {"accountant","createAccountant"})
  public void createAccountantUser() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users/create");
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).sendKeys("Test");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).sendKeys("Test");

    driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[2]/label/span[2]/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[2]/label/span[2]/select"))).selectByValue("accountant");

    driver.findElement(By.xpath("//input[@type='email']")).click();
    driver.findElement(By.xpath("//input[@type='email']")).clear();
    driver.findElement(By.xpath("//input[@type='email']")).sendKeys("test@test.test" + new Random().nextInt(10000));

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).sendKeys("test23703584");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).sendKeys("12345678");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).sendKeys("12345678");

    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();
  }

  @Test(groups = {"manager","createManager"})
  public void createManagerUser() {
    driver.navigate().to("https://shell-b2b.test.aurocraft.com/uk/users/create");
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[1]/label/input")).sendKeys("Test");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[2]/label/input")).sendKeys("Test");

    driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[2]/label/span[2]/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='root']/div/div[3]/div/div/div[2]/div[2]/div/div/div/div/div[2]/div[2]/label/span[2]/select"))).selectByValue("manager");

    driver.findElement(By.xpath("//input[@type='email']")).click();
    driver.findElement(By.xpath("//input[@type='email']")).clear();
    driver.findElement(By.xpath("//input[@type='email']")).sendKeys("test@test.test" + new Random().nextInt(10000));

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[4]/div[1]/label/input")).sendKeys("test23701904");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[1]/label/div/input")).sendKeys("12345678");

    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).click();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).clear();
    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div[2]/div[2]/div/div/div[1]/div/div[5]/div[2]/label/div/input")).sendKeys("12345678");

    driver.findElement(By.xpath("//button[@class='c-button c-button--primary']")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}

