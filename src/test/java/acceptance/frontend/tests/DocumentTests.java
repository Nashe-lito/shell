package acceptance.frontend.tests;

import acceptance.frontend.base.TestBase;
import org.testng.annotations.Test;

public class DocumentTests extends TestBase {

  @Test
  public void testSendRequestAct() throws Exception {
    app.getNavigationHelper().openActRequestPage();
    app.getDocumentHelper().selectStartOfPeriod();
    app.getDocumentHelper().selectEndOfPeriod();
    app.getDocumentHelper().clickOnSendButton();
 //   Thread.sleep(5000);
    app.getDocumentHelper().clickOnReturnToDocumentsButton();
  }

  @Test
  public void testInvoiceCustomRequestWithCreditBargaining() throws Exception {
    app.getNavigationHelper().openInvoiceCustomPage();
    app.getDocumentHelper().clickOnSendButton();
  //  Thread.sleep(5000);
    app.getDocumentHelper().clickOnReturnToDocumentsButton();
  }

  @Test
  public void testInvoiceCustomRequestWithAnotherAmount() throws Exception {
    app.getNavigationHelper().openInvoiceCustomPage();
    app.getDocumentHelper().selectRadioButtonAnotherAmount();
    app.getDocumentHelper().fillAnotherAmountField("100");
    app.getDocumentHelper().clickOnSendButton();
 //   Thread.sleep(3000);
    app.getDocumentHelper().clickOnReturnToDocumentsButton();
  }

  @Test
  public void testInvoiceCalculationRequest() throws Exception {
    app.getNavigationHelper().openInvoiceCalculationPage();
    app.getDocumentHelper().clickAddTMCButton();
    app.getDocumentHelper().selectAdnFillTMC("100");
    app.getDocumentHelper().clickOnSendButton();
   // Thread.sleep(3000);
    app.getDocumentHelper().clickOnReturnToDocumentsButton();
  }
}
