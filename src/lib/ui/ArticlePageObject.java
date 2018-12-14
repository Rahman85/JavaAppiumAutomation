package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObjects {

    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text = 'View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc ='More options']",
    ADD_TO_MY_LIST_BUTTON = "//*[@text ='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "//*[@text ='GOT IT']",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text ='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc ='Navigate up']";





    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 25);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT), "Can not find the end of the article", 20);
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find button to open article option",
                15);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Can not find option 'Add to reading list'",
                15);

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Can not find tip overlay 'GOT IT'",
                15);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can not find input to set name of article folder",
                15);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Can not put text into article folder input",
                15);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can not press 'OK' button",
                15);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can not close article, cannot find x link",
                15);
    }


}
