package com.demoWebShop.UIpgObj;

import com.demoWebShop.Models.Address_POJO;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

/**
 * Page Object for the Billing Address section of checkout.
 *
 * This class is responsible ONLY for interacting with the Billing Address form.
 * It does NOT click Continue — that is handled by Checkout_UIpgObj.
 *
 * All fields are filled using Address_POJO, keeping the flow fully data‑driven.
 */
public class BillingAddress_UIpgObj extends Base_UIPage {

    // -----------------------------
    // Billing Address Locators
    // -----------------------------
    private static final String FIRST_NAME = "#BillingNewAddress_FirstName";
    private static final String LAST_NAME = "#BillingNewAddress_LastName";
    private static final String EMAIL = "#BillingNewAddress_Email";
    private static final String COMPANY = "#BillingNewAddress_Company";
    private static final String COUNTRY = "#BillingNewAddress_CountryId";
    private static final String STATE = "#BillingNewAddress_StateProvinceId";
    private static final String CITY = "#BillingNewAddress_City";
    private static final String ADDRESS1 = "#BillingNewAddress_Address1";
    private static final String ADDRESS2 = "#BillingNewAddress_Address2";
    private static final String ZIP = "#BillingNewAddress_ZipPostalCode";
    private static final String PHONE = "#BillingNewAddress_PhoneNumber";
    private static final String FAX = "#BillingNewAddress_FaxNumber";

    public BillingAddress_UIpgObj(Page page) {
        super(page);
    }

    /**
     * Fills the Billing Address form using the provided Address_POJO.
     *
     * This method assumes the Billing Address section is already visible.
     * Visibility is handled by Checkout_UIpgObj.expandBillingAddressSection().
     */
    public void fillAddress(Address_POJO data) {
        Log.getLogger(getClass()).info("Filling Billing Address");

        type(FIRST_NAME, data.firstName);
        type(LAST_NAME, data.lastName);
        type(EMAIL, data.email);
        type(COMPANY, data.company);
        selectOption(COUNTRY, data.country);
        selectOption(STATE, data.state);
        type(CITY, data.city);
        type(ADDRESS1, data.address1);
        type(ADDRESS2, data.address2);
        type(ZIP, data.zip);
        type(PHONE, data.phone);
        type(FAX, data.fax);
    }
}
