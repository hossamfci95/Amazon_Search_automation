package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

public class SearchTest extends BaseTest {
    private HomePage homePage;
    private SearchResultsPage resultsPage;

    public SearchTest() {
        super();
    }

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver); // Now driver is initialized
        resultsPage = new SearchResultsPage(driver);
    }

    private void testSearchForProduct() {
        homePage.searchForItem("Laptop");
    }
//1
    @Test
    public void testSearchIsCorrect() throws InterruptedException {
        testSearchForProduct();
        String result = resultsPage.CheckSearchCorrect();
        Assert.assertTrue(result.contains("RAM"), "Search result validation failed!");
    }
//2
    @Test
    public void testSuggestionsIsShown() throws InterruptedException {
        homePage.EnterText("Laptop");
        Assert.assertTrue(resultsPage.CheckSuggestions(), "Suggestions not displayed correctly!");
    }
    //3
    @Test
    public void testMultipleFilters() throws InterruptedException {
        testSearchForProduct();
        resultsPage.applyFilter("//span[text()='HP']");  // Brand
        resultsPage.applyRamFilter();


Assert.assertTrue(resultsPage.verifyResultsContainText("16"),"Result text is not found");
    }
}
