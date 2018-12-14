import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;


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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        //SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning Programing";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();


        MyListsPageObjects MyListsPageObjects = new MyListsPageObjects(driver);

        //ArticlePageObject.waitForTitleElement();
        //ArticlePageObject.waitForTitleElement();

        MyListsPageObjects.waitForFolder(name_of_folder);
        MyListsPageObjects.openFolderByName(name_of_folder);
        MyListsPageObjects.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.InitSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.InitSearchInput();
        String search_line = "ertffgjtuyrujjuytjguy";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultlabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testChangeScreenOrientationSearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandccape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.InitSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitforSearchResult("Object-oriented programming language");
        this.backgroundApp(5);
        SearchPageObject.waitforSearchResult("Object-oriented programming language");
    }

}



