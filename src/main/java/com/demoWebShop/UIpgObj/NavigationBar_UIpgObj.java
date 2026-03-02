package com.demoWebShop.UIpgObj;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NavigationBar_UIpgObj extends Base_UIPage {

    private final Locator homeLink;
    private final Locator cartLink;
    private final Locator loginLink;
    private final Locator registerLink;

    private final Locator booksLink;

    public NavigationBar_UIpgObj(Page page) {
        super(page);

        this.homeLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).first();;
        //this.cartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shopping cart"));
        this.cartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Shopping cart")).first();
        this.loginLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log in")).first();;
        this.registerLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).first();;
        this.booksLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Books")).first();
        //this.booksLink = page.locator("ul.top-menu a[href='/books']");



    }

    public void goToBooks() {
        clickAndWaitForNavigation(booksLink, "Books link");
    }

    public void goToHome() {
        clickAndWaitForNavigation(homeLink, "Home link");
    }

    public void goToCart() {
        clickAndWaitForNavigation(cartLink, "Shopping Cart link");
    }

    public void goToCartAjax() {
        clickAndWaitForAjax(cartLink, "Shopping Cart link");
    }

    public void goToLogin() {
        clickAndWaitForNavigation(loginLink, "Login link");
    }

    public void goToRegister() {
        clickAndWaitForNavigation(registerLink, "Register link");
    }
    public boolean isNavBarVisible() {
        return homeLink.isVisible() &&
               cartLink.isVisible() &&
               loginLink.isVisible() &&
               registerLink.isVisible();
    }
    
}
