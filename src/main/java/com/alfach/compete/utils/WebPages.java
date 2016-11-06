package com.alfach.compete.utils;

import com.alfach.compete.browser.Browser;
import com.alfach.compete.pages.ulmartPages.*;

/**
 * Created by User on 11/3/2016.
 */
public class WebPages {

    private Browser browser;

    //Pages
    public Cart cart;
    public Catalog catalog;
    public Goods goods;
    public Home home;

    public WebPages(Browser browser) {
        this.browser = browser;
        initPages();
    }

    private void initPages(){
        cart = new Cart(this.browser);
        catalog = new Catalog(this.browser);
        goods = new Goods(this.browser);
        home = new Home(this.browser);
    }

}
