package com.demoWebShop.UIpgObj;

import com.microsoft.playwright.Page;

/**
 * Represents the Demo Web Shop home page.
 */
public class HomePage_UIpgObj extends Base_UIPage {

    private static final String HEADER_LOGO = "div.header-logo a";
    private static final String FOOTER = "div.footer";
    private static final String NAV_BAR = "div.header-menu";
    private static final String LOGIN_LINK = "a[href='/login']";
    private static final String REGISTER_LINK = "a[href='/register']";

    public HomePage_UIpgObj(Page page) {
        super(page);
    }

    public void open(String baseUrl) {
        page.navigate(baseUrl);
    }

    public boolean isHeaderVisible() {
        return isVisible(HEADER_LOGO);
    }

    public boolean isFooterVisible() {
        return isVisible(FOOTER);
    }

    public boolean isNavigationBarVisible() {
        return isVisible(NAV_BAR);
    }

    public void clickLogin() {
        click(LOGIN_LINK, "Click login link");
    }

    public void clickRegister() {
        click(REGISTER_LINK, "Register link");
    }
    public boolean isHomePage() {
        return isVisible("div.topic-block-title:has-text('Welcome to our store')");
    }
    
}
