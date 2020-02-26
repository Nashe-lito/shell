package acceptance.backend.tests;

import acceptance.backend.model.FiltersData;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FiltersTests extends TestBaseAdmin {


  @Test(priority = 0)
  public void testFiltersOnTheCompanyPage() throws Exception
  {
    appManager.getNavHelper().openCompanyPage();
    appManager.getFilterHelper().waitAndClickFilterContainer();
    appManager.getFilterHelper().fillFieldClientId("кв-0001332");
    appManager.getFilterHelper().fillFieldClientName("нек-сус");
    appManager.getFilterHelper().fillFieldcardNumber("2101039");
    appManager.getFilterHelper().fillFielsManagerId(new FiltersData("квц0000161"));
    appManager.getFilterHelper().selectType("1");
    appManager.getFilterHelper().selectStatus("0");
    appManager.getFilterHelper().selectRegistration("registered");
    appManager.getFilterHelper().clickOnFilterButton();

  //  assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("КВ-0001332"));
  }

  @Test(priority = 1)
  public void testFiltersOnClientUsersPage() throws Exception{
    appManager.getNavHelper().openUserOfCompanyPage();
    appManager.getFilterHelper().waitAndClickFilterContainer();
    appManager.getFilterHelper().selectRole("адмін");
    appManager.getFilterHelper().selectUsersStatus("активний");
    appManager.getFilterHelper().fillFieldCompany("Нек-Сус");
    appManager.getFilterHelper().fillFieldClient1cId("КВ-0001332");
    appManager.getFilterHelper().fillFieldEmail("test@test.test");
    appManager.getFilterHelper().fillFieldFullName("Surname");
    appManager.getFilterHelper().clickOnFilterButton();

    //    assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("test@test.test"));
  }

  @Test(priority = 2)
  public void testFiltersOnClientCardsPage() throws Exception{
    appManager.getNavHelper().openClientOfCompanyPage();
    appManager.getFilterHelper().waitAndClickFilterContainer();
    appManager.getFilterHelper().fillFieldClientId("кв-0001332");
    appManager.getFilterHelper().fillFieldClientName("нек-сус");
    appManager.getFilterHelper().fillFieldcardNumber("2101039");
    appManager.getFilterHelper().selectStatus("1");
    appManager.getFilterHelper().clickOnFilterButton();

    //   assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().contains("2101039"));
  }

  @Test(priority = 3)
  public void testClientsTransactionsPage() throws Exception{
    appManager.getNavHelper().openClientsTransactionsPage();
    appManager.getFilterHelper().waitAndClickFilterContainer();
    appManager.getFilterHelper().fillFieldClientId("кв-0001332");
    appManager.getFilterHelper().fillFieldClientName("нек-сус");
    appManager.getFilterHelper().fillFieldcardNumber("2101039");
    appManager.getFilterHelper().fillFieldAzsName("АЗС WOG №47 м. Рівне, вул. Дубенська, 163 а");
    appManager.getFilterHelper().fillFieldDateFrom("2020-01-01");
    appManager.getFilterHelper().fillFieldDateTo("2020-02-20");
    appManager.getFilterHelper().selectType("write-off");
    appManager.getFilterHelper().selectGamGroup();
    appManager.getFilterHelper().selectGam();
    appManager.getFilterHelper().clickOnFilterButton();

    //   assertTrue(appManager.getFilterHelper().driver.findElement(By.xpath("//tbody/tr[1]/td[5]")).getText().contains("Бензин Mustang 92"));

  }

}
