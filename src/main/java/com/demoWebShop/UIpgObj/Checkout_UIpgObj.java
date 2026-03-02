package com.demoWebShop.UIpgObj;

import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * Page Object representing the entire Checkout page.
 *
 * Handles:
 *  - Billing Address
 *  - Shipping Address
 *  - Shipping Method
 *  - Payment Method
 *  - Payment Information
 *  - Order Confirmation
 *
 * Includes stability helpers to expand collapsed sections,
 * wait for DOM transitions, and avoid hidden-element timeouts.
 */
public class Checkout_UIpgObj extends Base_UIPage {

    // -----------------------------
    // Billing Address Locators
    // -----------------------------
    private static final String BILLING_CONTINUE_BUTTON =
            "#billing-buttons-container .new-address-next-step-button";

    // -----------------------------
    // Shipping Address Locators
    // -----------------------------
    private static final String SHIPPING_CONTINUE_BUTTON =
            "#shipping-buttons-container .new-address-next-step-button";

    // -----------------------------
    // Shipping Method Locators
    // -----------------------------
    private static final String SHIPPING_METHOD_CONTINUE_BUTTON =
            "#shipping-method-buttons-container .shipping-method-next-step-button";

    // -----------------------------
    // Payment Method Locators
    // -----------------------------
    private static final String PAYMENT_METHOD_CONTINUE_BUTTON =
            "#payment-method-buttons-container .payment-method-next-step-button";

    // -----------------------------
    // Payment Info Locators
    // -----------------------------
    private static final String PAYMENT_INFO_CONTINUE_BUTTON =
            "#payment-info-buttons-container .payment-info-next-step-button";

    // -----------------------------
    // Confirm Order Locators
    // -----------------------------
    private static final String CONFIRM_ORDER_BUTTON =
            "#confirm-order-buttons-container .confirm-order-next-step-button";

    private static final String ORDER_SUCCESS_MESSAGE =
            "div.section.order-completed";

    // -----------------------------
    // Sub‑Page Objects
    // -----------------------------
    private final BillingAddress_UIpgObj billingAddress;
    private final ShippingAddress_UIpgObj shippingAddress;

    private final PaymentInfo_UIpgObj paymentInfo;
   
    
    public Checkout_UIpgObj(Page page) {
        super(page);
        this.billingAddress = new BillingAddress_UIpgObj(page);
        this.shippingAddress = new ShippingAddress_UIpgObj(page);
        this.paymentInfo = new PaymentInfo_UIpgObj(page);
    }

    // ========================================================================
    // SECTION EXPANSION HELPERS
    // ========================================================================

    /**
     * Ensures the Shipping Address section is expanded.
     *
     * Demo Web Shop collapses this section until:
     *  - Billing Continue is clicked
     *  - OR "Ship to the same address" is unchecked
     *
     * If you try to type into hidden fields, Playwright times out.
     */
    /**
     * Optional helper — ensures Billing Address section is visible.
     * Useful if the site collapses it after AJAX transitions.
     */
    private void expandBillingAddressSection() {
        page.waitForSelector(
            "#billing-addresses-form",
            new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE)
        );
        AllureUtils.addStep("Billing Address section expanded");
        Log.getLogger(getClass()).info("Billing Address section expanded");        
    }
    
    
    // ========================================================================
    // MAIN CHECKOUT FLOW
    // ========================================================================

    public void completeCheckoutWithDefaults(Checkout_POJO data) {

        // ------------------------------------------------------------
        // STEP 1 — BILLING ADDRESS
        // ------------------------------------------------------------
        waitForStep("Billing address");
        //expandBillingAddressSection(); // ⭐ ensures fields are visible

        billingAddress.fillAddress(data.billingAddress);

        clickAndWaitForAjax(
                page.locator(BILLING_CONTINUE_BUTTON),
                "Continue Billing"
        );
        smartStepWait();

	     // ------------------------------------------------------------
	     // STEP 2 — SHIPPING ADDRESS
	     // ------------------------------------------------------------
	     waitForStep("Shipping address");
	
	     // If a saved address dropdown is shown, switch to "New Address"
	     Locator shippingDropdown = page.locator("#shipping-address-select");
	     if (shippingDropdown.isVisible()) {
	         shippingDropdown.selectOption("New Address");
	         page.waitForSelector("#shipping-new-address-form",
	                 new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
	     }
	
	     // Now the form is visible — fill it
	     shippingAddress.fillAddress(data.shippingAddress);
	
	        clickAndWaitForAjax(
	                page.locator(SHIPPING_CONTINUE_BUTTON),
	                "Continue Shipping Address"
	        );

	     smartStepWait();

        // ------------------------------------------------------------
        // STEP 2 — SHIPPING ADDRESS
        // ------------------------------------------------------------
        /*waitForStep("Shipping address");

        //expandShippingAddressSection(); // ⭐ CRITICAL FIX

        shippingAddress.fillAddress(data.shippingAddress);

        clickAndWaitForAjax(
                page.locator(SHIPPING_CONTINUE_BUTTON),
                "Continue Shipping Address"
        );
        smartStepWait();*/


        // ------------------------------------------------------------
        // STEP 3 — SHIPPING METHOD
        // ------------------------------------------------------------
        waitForStep("Shipping method");

        selectRadioOption(data.shippingMethodCss, "Select Shipping Method");

        clickAndWaitForAjax(
                page.locator(SHIPPING_METHOD_CONTINUE_BUTTON),
                "Continue Shipping Method"
        );
        smartStepWait();


	        // ------------------------------------------------------------
	        // STEP 4 — PAYMENT METHOD
	        // ------------------------------------------------------------
	        /*waitForStep("Payment method");
	
	        selectRadioOption(data.paymentMethodCss, "Select Payment Method");
	
	        clickAndWaitForAjax(
	                page.locator(PAYMENT_METHOD_CONTINUE_BUTTON),
	                "Continue Payment Method"
	        );
	        smartStepWait();*/

     // ------------------------------------------------------------
     // STEP 4 — PAYMENT METHOD
     // ------------------------------------------------------------
     waitForStep("Payment method");

     // Select the Credit Card option (strict‑mode safe)
     retryClick("#checkout-payment-method-load li:has-text('Credit Card') input[type='radio']",
                "Select Credit Card");

     // Verify it is selected
     page.waitForSelector("#checkout-payment-method-load li:has-text('Credit Card') input[type='radio']");

     // Continue
     clickAndWaitForAjax(PAYMENT_METHOD_CONTINUE_BUTTON, "Continue Payment Method");
     smartStepWait();


       
        
		 // ------------------------------------------------------------
		 // STEP 5 — PAYMENT INFORMATION (Visa)
		 // ------------------------------------------------------------
		 /*waitForStep("Payment information");
		
		 // Fill Visa fields
		 paymentInfo.fillVisaPaymentInfo(data);
		
		 clickAndWaitForAjax(
		         page.locator(PAYMENT_INFO_CONTINUE_BUTTON),
		         "Continue Payment Info"
		 );
		 smartStepWait();*/
		  // ------------------------------------------------------------
		  // STEP 5 — PAYMENT INFORMATION
		  // ------------------------------------------------------------
		  waitForStep("Payment information");
	
		  // Only fill Visa fields if Credit Card is selected
		  if (data.paymentMethodCss.contains("paymentmethod_1")) {
		      paymentInfo.fillVisaPaymentInfo(data);
		  }
	
		  clickAndWaitForAjax(PAYMENT_INFO_CONTINUE_BUTTON, "Continue Payment Info");
		  smartStepWait();

		// ------------------------------------------------------------
		// STEP 6 — CONFIRM ORDER
		// ------------------------------------------------------------
		waitForStep("Confirm order");

		ConfirmOrder_UIpgObj confirm = new ConfirmOrder_UIpgObj(page);

		// Optional: verify product data
		Log.getLogger(getClass()).info("Product: " + confirm.getProductName());
		Log.getLogger(getClass()).info("Price: " + confirm.getProductPrice());
		Log.getLogger(getClass()).info("Qty: " + confirm.getProductQty());
		Log.getLogger(getClass()).info("Total: " + confirm.getProductTotal());

		// Optional: verify payment method
		Log.getLogger(getClass()).info("Payment Method: " + confirm.getPaymentMethod());

		// Optional: verify shipping method
		Log.getLogger(getClass()).info("Shipping Method: " + confirm.getShippingMethod());

		// Confirm the order
		//confirm.confirmOrder();

		smartStepWait();
		 
    }

    // ========================================================================
    // ORDER COMPLETION CHECK
    // ========================================================================

    public boolean isOrderCompleted() {
        return isVisible(ORDER_SUCCESS_MESSAGE);
    }
}
