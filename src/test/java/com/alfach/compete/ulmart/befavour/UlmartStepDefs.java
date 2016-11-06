package com.alfach.compete.ulmart.befavour;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.conf.Configuration;
import com.alfach.compete.utils.WebPages;
import com.gargoylesoftware.htmlunit.Page;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import org.testng.Assert;

import java.util.List;

/**
 * Created by User on 11/4/2016.
 */
@StepDefAnnotation
public class UlmartStepDefs {

    private String browserType = System.getProperty(Configuration.BROWSER_TYPE_PROP_NAME);

    private Browser browser;
    private WebPages pages;

    private String productName;

    @Before
    public void setup() {
        browser = new Browser(browserType);
        browser.clearCookies();
        pages = new WebPages(this.browser);
    }

    @After
    public void tearDown(){
        browser.close();
    }

    @Given("^Home page is opened$")
    public void home_page_is_opened() throws Throwable {
        pages.home.open();
        Assert.assertTrue(pages.home.isOpened());
    }

    @When("^\"([^\"]*)\" is opened")
    public void sport_and_tourism_is_opened(String category) throws Throwable {
        pages.home.openCategory(category);
        Assert.assertTrue(pages.catalog.isOpened());
    }

    @When("^Navigated to \"([^\"]*)\" by \"([^\"]*)\"$")
    public void navigated_to_by(String block, String subcategory) throws Throwable {
        pages.catalog.openSubcategory(block, subcategory);
        Assert.assertTrue(pages.catalog.isOpened());
    }

    @Then("^\"([^\"]*)\" category contains (\\d+) entries$")
    public void category_contains_entries(String category, int amount) throws Throwable {
        int size = pages.catalog.getCategorySize(category);
        Assert.assertEquals(size, amount);
    }

    @Then("^\"([^\"]*)\" category exists of \"([^\"]*)\"$")
    public void category_exists_of(String category, String subcats) throws Throwable {
        List<String> categories = pages.catalog.getSubcategoriesList(category);
        Assert.assertEquals(categories.toString(), subcats);

    }

    @Then("^List of goods contains (\\d+) card$")
    public void list_of_goods_contains_card(int amount) throws Throwable {
        Assert.assertEquals(amount, pages.catalog.amountOfGoodsOnPage());
    }

    @When("^Navigated to (\\d+) card$")
    public void navigated_to_card(int cardNumber) throws Throwable {
        pages.catalog.openCard(cardNumber);
        Assert.assertTrue(pages.goods.isOpened());
    }

    @Then("^Article equals \"([^\"]*)\"$")
    public void equals(String article) throws Throwable {
        Assert.assertEquals(pages.goods.getArticle(), article);
    }

    @When("^'Наименование товара' assigned to 'productName'")
    public void assigned_to() throws Throwable {
        productName = pages.goods.getProductName();
        Assert.assertNotNull(productName, "There is no product name assigned");
    }

    @When("^Navigated to buy good$")
    public void navigated_to_buy_good() throws Throwable {
        pages.goods.buy();
        Assert.assertTrue(pages.goods.isOpened(), "Goods page wasn't opened");
    }

    @Then("^\"([^\"]*)\" good is in cart$")
    public void good_is_in_cart(String arg1) throws Throwable {
        Assert.assertEquals(productName, pages.goods.getProductNameInCart(), "Difference between product names in cart and from goods page");
    }

    @When("^Navigated to cart$")
    public void navigated_to_cart() throws Throwable {
        pages.goods.goToCart();
        Assert.assertTrue(pages.cart.isOpened(), "Cart page wasn't opened");
    }

    @Then("^Clear the cart$")
    public void clear_the_cart() throws Throwable {
        pages.cart.clearCart();
        Assert.assertTrue(pages.cart.isCartEmpty(), "Cart has some elements");

    }

}
