import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class FirstTest extends CoreTestCase {


    @Test
    public void testSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                10);

       waitForElementPresent(
               By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
               "Can not find 'Object oriented programming language' topic searching by 'Java'" ,
               15);
    }

    @Test
    public void testGetText()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementPresent(
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
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                10);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                10);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                10);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                10);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "'X' is still preset on the page",
                10);
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                10);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                5);

        WebElement title_element = waitForElementPresent(
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
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                10);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text = 'Appium']"),

                "Cannot find 'Appium' article in search",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15);

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "Can not find the end of the article",
                20);

    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                15);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not find article title",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "Can not find button to open article option",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text ='Add to reading list']"),
                "Can not find option 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text ='GOT IT']"), //By.id("org.wikipedia:id/onboarding_button"),
                "Can not find tip overlay 'GOT IT'",
                15);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of article folder",
                15);


        String name_of_folder = "Learning Programing";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Can not put text into article folder input",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text ='OK']"),
                "Can not press 'OK' button",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc ='Navigate up']"),
                "Can not close article, cannot find x link",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc ='My lists']"),
                "Can not find Navigation button to My lists",
                15);

        waitForElementPresent(
                By.xpath("//*[@text ='" + name_of_folder + "']"),
                "Can not find created folder",
                25);


        waitForElementAndClick(
                By.xpath("//*[@text ='" + name_of_folder + "']"),
                "Can not find created folder",
                25);

        waitForElementPresent(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot find saved article",
                10);


        swipeElementToLeft(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot find saved article",
                20);

        waitForElementNotPresent(
                By.xpath("//*[@text ='Java (programming language)']"),
                "cannot delete saved article",
                15);

    }

    @Test

    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by request " + search_line,
                15);

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);

    }

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "edrftgtyhgfopasdfghhjkl";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text = 'No results found']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_line,
                10);

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line);

    }

    @Test
    public void testChangeScreenOrientationSearchResult()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                15);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find 'Object oriented programming language' topic searching by " + search_line,
                15);

        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of aticle",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of aticle",
                15);

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
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

    public void testCheckSearchArticleInBackground()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                15);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find search Wikipedia input",
                15);
        driver.runAppInBackground(5);


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),

                "Cannot find article after returning from background",
                15);


    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 25);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }


    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe) {


        TouchAction action = new TouchAction(driver);

        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);


        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release().perform();

    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_Swipe)
    {

        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if(already_swiped > max_Swipe){
                waitForElementPresent(by, "Can not find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message, long timeoutInSeconds)
    {
       WebElement element = waitForElementPresent(
               by,
               error_message,
               20);

       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(700)
                .moveTo(left_x, middle_y)
                .release()
                .perform();


    }
    private int getAmountOfElements(By by)
    {

        List elements = driver.findElements(by);
        return elements.size();

    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' Supposed not to be present";
            throw new AssertionError(default_message + " " + error_message);

        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}



