package com.alfach.compete.pages.ulmartPages;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by User on 11/3/2016.
 */
public class Catalog extends BasePage {

    private Map<String, List<WebElement>> categories ;

    private String allCategoriesCss = "div.col-main-4";

    @FindBy(how = How.CSS, using = "div.col-main-4 li")
    private List<WebElement> allCategories;

    @FindBy(how = How.CSS, using = ".main-h1")
    private List<WebElement> mainHeader;

    @FindBy(how = How.CSS, using = ".b-truncate.helper-block .must_be_href.js-gtm-product-click")
    private List<WebElement> goodsSections;

    public Catalog(Browser browser){super(browser);}

    public int getCategorySize(String block){
        mapCategories();
        List<WebElement> rList = categories.get(block);
        rList = rList.stream().filter(x -> !x.getText().contains("Все категории")).collect(Collectors.toList());
        return rList.size() - 1;
    }

    public List<String> getSubcategoriesList(String category){
        mapCategories();
        List<String> result = new ArrayList<>();
        List<WebElement> filteredList = categories.get(category);
        result = filteredList.stream()
                .filter(x -> !x.getText().contains(category))
                .filter(x -> !x.getText().contains("Все категории"))
                .map(x -> x.getText()).collect(Collectors.toList());
        return result;
    }

    public int amountOfGoodsOnPage(){
        return goodsSections.size();
    }

    public void openCard(int cardNumber) {
        browser.getDriver().navigate().to(goodsSections.get(cardNumber - 1).getAttribute("href"));
    }

    public void openSubcategory(String block, String subcategory){
        if(isCommercialLoaded())
            closeCommercial();
        mapCategories();
        try {
            for (WebElement w : categories.get(block))
                if (w.getText().contains(subcategory))
                    w.click();
        }
        catch (StaleElementReferenceException e) {}
    }

    //puts List of elements inside categories(Тренажеры, Спортивное питание и т.п.)
    private void mapCategories(){
        categories = new HashMap();
        int delimiterCounter = 0;
        String allCats = "";
        List<WebElement> categoriesDivided = new LinkedList();

        //Walk through 3 <ul>s on page.
        for (int i = 1 ; i < 4; i++) {
            //Walk through every <li> element representing categories. Gather it into List and put to Map
            for (WebElement w : browser.getDriver().findElements(By.cssSelector(allCategoriesCss + ":nth-child(" + i + ") li")))
                try {
                    if (delimiterCounter == 0)
                        allCats = w.getText();
                    categoriesDivided.add(w.findElement(By.tagName("a")));
                    delimiterCounter++;
                } catch (Exception e) {
                    categories.put(allCats, categoriesDivided);
                    categoriesDivided = new LinkedList();
                    delimiterCounter = 0;
                }
            categories.put(allCats, categoriesDivided);
            categoriesDivided = new LinkedList();
            delimiterCounter = 0;
        }
    }

}
