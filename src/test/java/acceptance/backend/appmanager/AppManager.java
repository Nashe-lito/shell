package acceptance.backend.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppManager {
  private final Properties properties;
  WebDriver driver;

  private CompanyPageHelper companyPageHelper;
  private UserHelper userHelper;
  private SessHelper sessHelper;
  private NavHelper navHelper;
  private FilterHelper filterHelper;
  private UsersOfAdminHelper usersOfAdminHelper;

  public StringBuffer verificationErrors = new StringBuffer();
  private String browser;

  public AppManager(String browser) {
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


    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      driver.get(properties.getProperty("web.baseUrlAdmin"));
      Dimension d = new Dimension(1400, 877);
      driver.manage().window().setSize(d);
    filterHelper = new FilterHelper(driver);
    navHelper = new NavHelper(driver);
    sessHelper = new SessHelper(driver);
    companyPageHelper = new CompanyPageHelper(driver);
    userHelper = new UserHelper(driver);
    usersOfAdminHelper = new UsersOfAdminHelper(driver);
    sessHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }


  public void stop() {
    driver.quit();
  }

  public FilterHelper getFilterHelper() {
    return filterHelper;
  }

  public NavHelper getNavHelper() {
    return navHelper;
  }

  public CompanyPageHelper getCompanyPageHelper() {
    return companyPageHelper;
  }

  public UserHelper getUserHelper() {
    return userHelper;
  }

  public UsersOfAdminHelper getUsersOfAdminHelper(){
    return usersOfAdminHelper;
  }
}


