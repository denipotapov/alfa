package com.alfach.compete.pages;

import com.alfach.compete.browser.Browser;
import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 11/3/2016.
 */
public class BasePage  {

    public static long maxWaitTimeSec = 20;
    public static long waitIntervalSec = 1;

    @FindBy(how = How.CSS, using = ".b-head-logo > a:nth-child(1)")
    WebElement brandLogo;

    @FindBy(how = How.CSS, using = ".sp-fancybox-iframe")
    WebElement commercial;

    protected Browser browser;

    //Do not delete me or make private. Need to init elements by PageFactory reflective invocation
    public BasePage(Browser browser){
        this.browser = browser;
        PageFactory.initElements(browser.getDriver(), this);
    }

    public void waitForLoad() {
        new WebDriverWait(browser.getDriver(), maxWaitTimeSec).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForAlert(long seconds)
    {
        new WebDriverWait(browser.getDriver(), maxWaitTimeSec, waitIntervalSec)
                .until(ExpectedConditions.alertIsPresent());
    }

    public boolean isCommercialLoaded(){
        boolean isLoaded;
        try {
            new WebDriverWait(browser.getDriver(), 15).until(ExpectedConditions.elementToBeClickable(commercial));
            isLoaded = true;
        }
        catch (Exception e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    public void closeCommercial(){
        Actions acts = new Actions(browser.getDriver());
        acts.moveByOffset(brandLogo.getLocation().x, brandLogo.getLocation().getY()).click().build().perform();

    }

    public boolean isOpened(){
        boolean isOpened = false;
        try {
            waitForLoad();
            if (!browser.getDriver().getTitle().equals("Problem loading page") && brandLogo.isEnabled())
                isOpened = true;
        }
        catch (Exception e) {
            isOpened = false;
        }
        return isOpened;
    }
}
