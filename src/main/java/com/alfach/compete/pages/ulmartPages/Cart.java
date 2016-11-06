package com.alfach.compete.pages.ulmartPages;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.pages.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by User on 11/3/2016.
 */
public class Cart extends BasePage {

    @FindBy(how = How.CSS, using = "button.gray:nth-child(2)")
    private WebElement clearCart;

    @FindBy(how = How.CSS, using = ".emptyCart > p:nth-child(2)")
    private WebElement cartDefinition;

    public Cart(Browser browser){super(browser);}

    public void clearCart(){
        waitForLoad();
        clearCart.click();
        waitForAlert(maxWaitTimeSec);
        browser.getDriver().switchTo().alert().accept();
    }

    public boolean isCartEmpty(){
        return cartDefinition.getText().contains("Корзина пуста");
    }

}
