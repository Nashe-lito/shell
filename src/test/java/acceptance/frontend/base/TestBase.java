package acceptance.frontend.base;

import acceptance.frontend.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;

import static org.testng.Assert.fail;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {


    app.stop();
    String verificationErrorString = app.verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }

  }
  public ApplicationManager getApp() {
    return app;
  }
}
