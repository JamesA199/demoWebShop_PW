package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ConfirmOrder_UIpgObj extends Base_UIPage {

    // ⭐ Use ONE root — your environment uses this ID
    private static final String ROOT = "#checkout-confirm-order-load";
    

    private static final String BILLING_INFO    = ROOT + " .billing-info";
    private static final String SHIPPING_INFO   = ROOT + " .shipping-info";
    private static final String PAYMENT_METHOD  = ROOT + " .payment-method";
    private static final String SHIPPING_METHOD = ROOT + " .shipping-method";

    private static final String PRODUCT_NAME  = ROOT + " td.product a";
    private static final String PRODUCT_PRICE = ROOT + " td.unit-price";
    private static final String PRODUCT_QTY   = ROOT + " td.qty span:last-child";
    private static final String PRODUCT_TOTAL = ROOT + " td.subtotal";

    // ⭐ Confirm button — your screenshot shows this exact class
    //private static final String CONFIRM_BUTTON = ROOT + " input.confirm-order-next-step-button";
    private static final String CONFIRM_BUTTON = "input.confirm-order-next-step-button";

    public ConfirmOrder_UIpgObj(Page page) {
        super(page);
    }

    public String getBillingInfo() { return getText(BILLING_INFO); }
    public String getShippingInfo() { return getText(SHIPPING_INFO); }
    public String getPaymentMethod() { return getText(PAYMENT_METHOD); }
    public String getShippingMethod() { return getText(SHIPPING_METHOD); }

    public String getProductName() { return getText(PRODUCT_NAME); }
    public String getProductPrice() { return getText(PRODUCT_PRICE); }
    public String getProductQty() { return getText(PRODUCT_QTY); }
    public String getProductTotal() { return getText(PRODUCT_TOTAL); }

    public ThankYou_UIpgObj confirmOrder() {
    	
        // Wait for the please-wait overlay to be hidden
        Locator overlay = page.locator("#confirm-order-please-wait");
        overlay.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));    	
        retryClick(CONFIRM_BUTTON, "Confirm Order");
        AllureUtils.addStep("Click Confirm button.");
        Log.getLogger(getClass()).info("Click Confirm button (confirmOrder)");

        return new ThankYou_UIpgObj(page);
    }
}