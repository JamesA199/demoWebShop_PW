package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.Header_UIpgObj;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

public class Header_UserFlow extends Base_UFPage {

    private final Header_UIpgObj header;

    public Header_UserFlow(Page page) {
        super(page);
        this.header = new Header_UIpgObj(page);
    }

    public void logout() {
        header.logout();
        Log.getLogger(getClass()).info("Click 'logout' button (logout).");
    }
}
