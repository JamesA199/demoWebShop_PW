package com.demoWebShop.Tests.UI;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.UIpgObj.HomePage_UIpgObj;
import com.demoWebShop.UIpgObj.NavigationBar_UIpgObj;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke tests: basic navigation and layout checks.
 */
public class SmokeTests_UI extends BaseTest {

    @Test(description = "Load homepage and validate title", groups = {"smoke"})
    public void loadHomePage() {
        HomePage_UIpgObj home = new HomePage_UIpgObj(getPage());
        Assert.assertTrue(home.isHeaderVisible(), "Header should be visible");
        Assert.assertTrue(home.isFooterVisible(), "Footer should be visible");
    }

    @Test(description = "Navigate to login page from home", groups = {"smoke"})
    public void navigateToLogin() {
        HomePage_UIpgObj home = new HomePage_UIpgObj(getPage());
        home.clickLogin();
        Assert.assertTrue(getPage().url().contains("/login"), "URL should contain /login");
    }

    @Test(description = "Navigate to register page from home", groups = {"smoke"})
    public void navigateToRegister() {
        HomePage_UIpgObj home = new HomePage_UIpgObj(getPage());
        home.clickRegister();
        Assert.assertTrue(getPage().url().contains("/register"), "URL should contain /register");
    }

    @Test(description = "Validate header, footer, and navigation bar", groups = {"smoke"})
    public void validateHeaderFooterAndNavBar() {
        HomePage_UIpgObj home = new HomePage_UIpgObj(getPage());
        NavigationBar_UIpgObj nav = new NavigationBar_UIpgObj(getPage());

        Assert.assertTrue(home.isHeaderVisible(), "Header should be visible");
        Assert.assertTrue(home.isFooterVisible(), "Footer should be visible");
        Assert.assertTrue(nav.isNavBarVisible(), "Navigation bar should be visible");
    }
}
