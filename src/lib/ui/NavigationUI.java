package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObjects {

    private static final String

    MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc ='My lists']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList() {


        this.waitForElementAndClick(
                By.xpath(MY_LISTS_LINK),
                "Can not find Navigation button to My lists",
                15);

    }

}
