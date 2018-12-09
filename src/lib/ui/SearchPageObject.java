package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObjects {

    private static final String

     SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
     SEARCH_INPUT = "//*[contains(@text,'Search…')]",
     SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = '(SUBSTRING)']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getSearchInitElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("(SUBSTRING)", substring);
    }
    /* TEMPLATE METHODS */

    public void InitSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 15);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 15);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 15);
    }

    public void waitforSearchResult(String substring)
    {
        String search_result_xpath = getSearchInitElement(substring);

        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }
}
