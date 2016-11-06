package com.alfach.compete.pages.ulmartPages;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 11/3/2016.
 */
public class Home extends BasePage {

    public static final String URL = System.getProperty("home.url");

    @FindBy(how = How.CSS, using = "li.b-dropdown:nth-child(17) > a:nth-child(1)")
    WebElement sportAndTourismLink;

    @FindBy(how = How.CSS, using = ".b-list__item.dropdown_catalog-menu")
    List<WebElement> categories;

    public Home(Browser browser){super(browser);}

    public void open() {
        this.browser.getDriver().get(URL);
    }

    public void openCategory(String category) {
        for(WebElement w: categories){
            if (w.getText().equals(category)) {
                w.click();
                break;
        }}
    }

}
