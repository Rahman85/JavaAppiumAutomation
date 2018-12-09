import lib.CoreTestCase;
import lib.ui.MainPageObjects;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {

    private MainPageObjects MainPageObjects;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObjects = new MainPageObjects(driver);
    }


    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitforSearchResult("Object-oriented programming language");
    }

    @Test
    public void testGetText() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        MainPageObjects.waitForElementPresent(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cannot find such text",
                10);

        String text = driver.findElement(By.xpath("//*[contains(@text,'Search…')]")).getText();
        System.out.println(text);
        Assert.assertEquals(
                "Search…",
                text);
    }

    @Test
    public void testCancelSearch() {
        MainPageObjects.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                10);

        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                10);

        MainPageObjects.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10);

        MainPageObjects.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                10);

        MainPageObjects.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "'X' is still preset on the page",
                10);
    }

    @Test
    public void testCompareArticleTitle() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                10);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                5);

        WebElement title_element = MainPageObjects.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15);
        String title_article = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                title_article);
    }

    @Test
    public void testSwipeArticle() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                10);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),

                "Cannot find 'Appium' article in search",
                5);

        MainPageObjects.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15);

        MainPageObjects.swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Can not find the end of the article",
                20);
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                15);

        MainPageObjects.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "Can not find button to open article option",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@text ='Add to reading list']"),
                "Can not find option 'Add to reading list'",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@text ='GOT IT']"), //By.id("org.wikipedia:id/onboarding_button"),
                "Can not find tip overlay 'GOT IT'",
                15);

        MainPageObjects.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of article folder",
                15);

        String name_of_folder = "Learning Programing";
        MainPageObjects.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can not put text into article folder input",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@text ='OK']"),
                "Can not press 'OK' button",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc ='Navigate up']"),
                "Can not close article, cannot find x link",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc ='My lists']"),
                "Can not find Navigation button to My lists",
                15);

        MainPageObjects.waitForElementPresent(
                By.xpath("//*[@text ='" + name_of_folder + "']"),
                "Can not find created folder",
                25);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@text ='" + name_of_folder + "']"),
                "Can not find created folder",
                25);

        MainPageObjects.waitForElementPresent(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot find saved article",
                10);

        MainPageObjects.swipeElementToLeft(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot find saved article",
                20);

        MainPageObjects.waitForElementNotPresent(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot delete saved article",
                15);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "Linkin Park Discography";
        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        MainPageObjects.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by request " + search_line,
                15);

        int amount_of_search_results = MainPageObjects.getAmountOfElements(
                By.xpath(search_result_locator));
        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "edrftgtyhgfopasdfghhjkl";
        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text = 'No results found']";

        MainPageObjects.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                10);

        MainPageObjects.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationSearchResult() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "Java";
        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find 'Object oriented programming language' topic searching by " + search_line,
                15);

        String title_before_rotation = MainPageObjects.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of aticle",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = MainPageObjects.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of aticle",
                15);

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = MainPageObjects.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of aticle",
                15);

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        MainPageObjects.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        MainPageObjects.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                15);

        MainPageObjects.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                15);
        driver.runAppInBackground(5);

        MainPageObjects.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find article after returning from background",
                15);
    }

}



