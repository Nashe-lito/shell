package acceptance.backend.tests;

import acceptance.backend.appmanager.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Random;

import static org.testng.Assert.fail;

public class TestBaseAdmin {

  protected final AppManager appManager = new AppManager();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    appManager.init();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    appManager.stop();
    String verificationErrorString = appManager.getFilterHelper().verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }

  }

  public AppManager getAppManager() {
    return appManager;
  }
}
