package com.demoWebShop.UIpgObj;

import com.microsoft.playwright.Page;

public class Header_UIpgObj extends Base_UIPage {

    private static final String LOGOUT_LINK = "a[href='/logout']";

    public Header_UIpgObj(Page page) {
        super(page);
    }

    public void logout() {
        click(LOGOUT_LINK, "Click Logout link");
        page.waitForURL("**/");
    }
}
