package acceptance.frontend.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
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
  }


  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
    driver = new ChromeDriver();
    }else if (browser.equals(BrowserType.SAFARI)) {
      driver = new SafariDriver();
    }

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    Dimension d = new Dimension(1400,877);
    driver.manage().window().setSize(d);
    userHelper = new UserHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    driverHelper = new DriverHelper(driver);
    cardHelper = new CardHelper(driver);
    documentHelper = new DocumentHelper(driver);
    sessionHelper.login("johndou", "111");
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
}
