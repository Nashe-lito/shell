package acceptance.frontend.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver driver;

  private SessionHelper sessionHelper;
  private UserHelper userHelper;
  private NavigationHelper navigationHelper;
  private DriverHelper driverHelper;
  private CardHelper cardHelper;
  private DocumentHelper documentHelper;

  public StringBuffer verificationErrors = new StringBuffer();
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
    driver = new ChromeDriver();
    }else if (browser.equals(BrowserType.SAFARI)) {
      driver = new SafariDriver();
    }

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrlPortal"));
    Dimension d = new Dimension(1400,877);
    driver.manage().window().setSize(d);
    userHelper = new UserHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    driverHelper = new DriverHelper(driver);
    cardHelper = new CardHelper(driver);
    documentHelper = new DocumentHelper(driver);
    sessionHelper.login(properties.getProperty("web.testLogin"), properties.getProperty("web.testPassword"));
  }


  public void stop() {
    driver.quit();
  }



  public UserHelper getUserHelper() {
    return userHelper;
  }

  public DriverHelper getDriverHelper() {
    return driverHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
  public CardHelper getCardHelper() {
    return cardHelper;
  }

  public DocumentHelper getDocumentHelper() {
    return documentHelper;
  }
  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

 /* public byte[] takeScreenshot(){
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }*/
}
