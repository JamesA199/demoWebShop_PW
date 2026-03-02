package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ThankYou_UIpgObj extends Base_UIPage {

    private static final String ROOT = "#checkout-order-completed, .order-completed";

    //private static final String SUCCESS_MESSAGE = ".order-completed .title strong";
    //private static final String ORDER_NUMBER    = ".order-completed .order-number strong";
    //private static final String CONTINUE_BUTTON = "input.order-completed-continue-button";

    private static final String SUCCESS_MESSAGE = ".order-completed .title strong"; 
    private static final String ORDER_NUMBER_ROW = ".order-completed .details li:has-text('Order number')"; 
    private static final String CONTINUE_BUTTON = "input.order-completed-continue-button";
    
    public ThankYou_UIpgObj(Page page) {
        super(page);
    }

    public boolean isSuccessMessageVisible() {
        AllureUtils.addStep("Success message is visible.");
        Log.getLogger(getClass()).info("Success message is visible: "+SUCCESS_MESSAGE);
        return isVisible(SUCCESS_MESSAGE);
    }

    public String getOrderNumber() {
        String row = getText(ORDER_NUMBER_ROW).trim(); // "Order number: 2229755"
        String number = row.replace("Order number:", "").trim();
        AllureUtils.addStep("Reterieved order number "+number+" from: "+ORDER_NUMBER_ROW);
        Log.getLogger(getClass()).info("Reterieved order number "+number+" from: "+ORDER_NUMBER_ROW);
        return number;
    }

    public HomePage_UIpgObj clickContinue() {
    	// ⭐ Wait for the Thank You section to fully load 
    	page.locator(".order-completed").waitFor( new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE) );    	
        retryClick(CONTINUE_BUTTON, "Click 'Thank-You Continue' button after order completion");
        AllureUtils.addStep("Click 'Thank-You Continue' button after order completion");
        Log.getLogger(getClass()).info("Click 'Thank-You Continue' button after order completion");
        return new HomePage_UIpgObj(page);
    }
}
