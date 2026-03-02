package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Represents the shopping cart page.
 */
public class Cart_UIpgObj extends Base_UIPage {

    private final Locator termsCheckbox;
    private final Locator checkoutButton;

    public Cart_UIpgObj(Page page) {
        super(page);
        this.termsCheckbox = page.locator("input#termsofservice");
        this.checkoutButton = page.locator("button#checkout");
    }

    public void proceedToCheckout() {
        Log.getLogger(getClass()).info("In method 'proceedToCheckout'.");
        termsCheckbox.check();
        click(checkoutButton, "Checkout button");
    }

}
