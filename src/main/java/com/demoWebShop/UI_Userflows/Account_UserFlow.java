package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.OrderHistory_UIpgObj;
import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

public class Account_UserFlow extends Base_UFPage {

    public Account_UserFlow(Page page) {
        super(page);
    }

    public OrderHistory_UIpgObj goToOrderHistory() {
    	
        page.click("a.account"); // top-right email link
        AllureUtils.addStep("Click 'email account' goto order history.");
        Log.getLogger(getClass()).info("Click 'email account' goto order history.");   
        
        page.click("a:has-text('Orders')");
        //by text: 'a:has-text("Orders")'
        //by href: a[href='/customer/orders']
        //by class: a.inactive:has-text("Orders")
        AllureUtils.addStep("Click 'orders' link.");
        Log.getLogger(getClass()).info("Click 'orders' link.");        
        return new OrderHistory_UIpgObj(page);
    }
}
