package acceptance.frontend.base;

import acceptance.frontend.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.testng.Assert.fail;
//@Listeners(MyTestListener.class)

public class TestBase {

 // Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
 // public void setUp(ITestContext context) throws Exception {
 public void setUp() throws Exception {
    app.init();
   // context.setAttribute("app", app);
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
    String verificationErrorString = app.verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

/*  @BeforeMethod
  public void logTestStart(Method m, Object [] p){
    logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test" + m.getName());
  }*/

  public ApplicationManager getApp() {
    return app;
  }
}
