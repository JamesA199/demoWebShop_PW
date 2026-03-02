package com.demoWebShop.UIpgObj;

import com.demoWebShop.Models.Address_POJO;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

/**
 * Page Object for the Shipping Address section of checkout.
 *
 * This class ONLY fills the form — it does not expand the section or click Continue.
 * Section expansion is handled by Checkout_UIpgObj.expandShippingAddressSection().
 */
public class ShippingAddress_UIpgObj extends Base_UIPage {

    // -----------------------------
    // Shipping Address Locators
    // -----------------------------
    private static final String FIRST_NAME = "#ShippingNewAddress_FirstName";
    private static final String LAST_NAME = "#ShippingNewAddress_LastName";
    private static final String EMAIL = "#ShippingNewAddress_Email";
    private static final String COMPANY = "#ShippingNewAddress_Company";
    private static final String COUNTRY = "#ShippingNewAddress_CountryId";
    private static final String STATE = "#ShippingNewAddress_StateProvinceId";
    private static final String CITY = "#ShippingNewAddress_City";
    private static final String ADDRESS1 = "#ShippingNewAddress_Address1";
    private static final String ADDRESS2 = "#ShippingNewAddress_Address2";
    private static final String ZIP = "#ShippingNewAddress_ZipPostalCode";
    private static final String PHONE = "#ShippingNewAddress_PhoneNumber";
    private static final String FAX = "#ShippingNewAddress_FaxNumber";

    public ShippingAddress_UIpgObj(Page page) {
        super(page);
    }

    /**
     * Fills the Shipping Address form using Address_POJO.
     *
     * The Shipping Address section MUST be expanded before calling this method.
     * If not expanded, Playwright will throw hidden-element timeouts.
     */
    public void fillAddress(Address_POJO data) {
        Log.getLogger(getClass()).info("Filling Shipping Address");

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
