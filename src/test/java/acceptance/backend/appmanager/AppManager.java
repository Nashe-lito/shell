package acceptance.backend.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AppManager {
  ChromeDriver driver;

  private CompanyPageHelper companyPageHelper;
  private UserHelper userHelper;
  private SessHelper sessHelper;
  private NavHelper navHelper;
  private FilterHelper filterHelper;

  public void init() {
    driver = new ChromeDriver();
    Dimension d = new Dimension(1400, 877);
    driver.manage().window().setSize(d);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    filterHelper = new FilterHelper(driver);
    navHelper = new NavHelper(driver);
    sessHelper = new SessHelper(driver);
    companyPageHelper = new CompanyPageHelper(driver);
    userHelper = new UserHelper(driver);
    sessHelper.login("root", "111");
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


}


