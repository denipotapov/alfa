package com.alfach.compete.pages.ulmartPages;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by User on 11/3/2016.
 */
public class Goods extends BasePage {

    @FindBy(how = How.CSS, using = ".b-art .b-art__num")
    private WebElement article;

    @FindBy(how = How.CSS, using = ".main-h1")
    private WebElement productName;

    @FindBy(how = How.CSS, using = "a.btn-lg")
    private WebElement buyLink;

    @FindBy(how = How.CSS, using = "div.b-product__title:nth-child(1) > a:nth-child(1)")
    private WebElement productInCartName;

    @FindBy(how = How.CSS, using = "a.gtm-checkout:nth-child(2)")
    private WebElement goToCart;

    public Goods(Browser browser){super(browser);}

    public String getArticle(){
        return article.getText();
    }

    public String getProductName(){
        return productName.getText();
    }

    public String getProductNameInCart() {
        return productInCartName.getText();
    }

    public void buy(){
        buyLink.click();
        waitForLoad();
    }

    public void goToCart(){
        goToCart.click();
        waitForLoad();
    }
}
