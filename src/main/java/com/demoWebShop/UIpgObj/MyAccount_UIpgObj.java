package com.demoWebShop.UIpgObj;

import com.microsoft.playwright.Page;

/**
 * Represents the "My account" page.
 */
public class MyAccount_UIpgObj extends Base_UIPage {

    private static final String CUSTOMER_INFO_LINK = "a[href='/customer/info']";

    public MyAccount_UIpgObj(Page page) {
        super(page);
    }

    public void goToCustomerInfo() {
        click(CUSTOMER_INFO_LINK, "Click customer info Link");
    }
}
