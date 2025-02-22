package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import java.util.Collections;
import java.util.List;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public String CheckSearchCorrect() throws InterruptedException {

        By SearchTerm = By.id("p_n_feature_thirty-three_browse-bin-title");
        WebElement SearchTermElement = driver.findElement(SearchTerm);


        return SearchTermElement.getText();


    }
    public boolean CheckSuggestions() throws InterruptedException {

        By Suggestion = By.id("sac-suggestion-row-1");
        WebElement SuggestionElement = driver.findElement(Suggestion);


        return SuggestionElement.isDisplayed();


    }
    public void applyFilter(String filterXPath) {

        WebElement filterElement =  driver.findElement(By.xpath(filterXPath));
        filterElement.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner"))); // Wait for page reload
    }
    public void applyRamFilter() {

        String ramSize = "16 GB";
        List<WebElement> ramFilters = driver.findElements(By.xpath("//span[contains(text(),'" + ramSize + "')]"));

        if (!ramFilters.isEmpty()) {
            ramFilters.get(0).click();
        } else {
            System.out.println("RAM filter not found!");
        } }


    public boolean verifyResultsContainText(String expectedText) {
        WebElement productTitles =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".a-size-medium.a-spacing-none.a-color-base.a-text-normal")
        ));

      Boolean found=productTitles.getText().contains(expectedText);
      System.out.println(productTitles.getText());

        Assert.assertTrue(found, "Filtered results do not contain the expected text: " + expectedText);
        return found;
    }
}
