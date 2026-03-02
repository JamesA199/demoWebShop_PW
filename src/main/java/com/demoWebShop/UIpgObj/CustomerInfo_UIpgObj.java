package com.demoWebShop.UIpgObj;

import com.microsoft.playwright.Page;

/**
 * Represents the customer info page where profile can be updated.
 */
public class CustomerInfo_UIpgObj extends Base_UIPage {

    private static final String FIRST_NAME = "input#FirstName";
    private static final String LAST_NAME = "input#LastName";
    private static final String EMAIL = "input#Email";
    private static final String SAVE_BUTTON = "input.button-1.save-customer-info-button";

    public CustomerInfo_UIpgObj(Page page) {
        super(page);
    }

    public void updateCustomerInfo(String firstName, String lastName, String email) {
        type(FIRST_NAME, firstName);
        type(LAST_NAME, lastName);
        type(EMAIL, email);
        click(SAVE_BUTTON, "Save customer info button");
    }
}
