package acceptance.backend.tests;

import acceptance.backend.appmanager.AppManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.fail;

public class TestBaseAdmin {

 // Logger logger = LoggerFactory.getLogger(TestBaseAdmin.class);

  protected static final AppManager appManager
          = new AppManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    appManager.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    appManager.stop();
    String verificationErrorString = appManager.getFilterHelper().verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

 /* @BeforeMethod
  public void logTestStart(Method m, Object [] p){
    logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test" + m.getName());
  }*/

  public AppManager getAppManager() {
    return appManager;
  }
}
