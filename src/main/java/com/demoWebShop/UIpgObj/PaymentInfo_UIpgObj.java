package com.demoWebShop.UIpgObj;

import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

/**
 * Page Object for the Payment Information section.
 *
 * Demo Web Shop supports multiple payment methods, but this class
 * currently implements Visa credit card entry.
 *
 * This keeps the checkout flow clean and modular.
 */
public class PaymentInfo_UIpgObj extends Base_UIPage {

    private static final String CARD_TYPE = "#CreditCardType";
    private static final String CARD_HOLDER = "#CardholderName";
    private static final String CARD_NUMBER = "#CardNumber";
    private static final String EXPIRE_MONTH = "#ExpireMonth";
    private static final String EXPIRE_YEAR = "#ExpireYear";
    private static final String CARD_CODE = "#CardCode";

    public PaymentInfo_UIpgObj(Page page) {
        super(page);
    }

    /**
     * Fills Visa credit card fields using Checkout_POJO.
     *
     * This method assumes:
     *  - Payment Method step has already selected "Credit Card"
     *  - Payment Information section is visible
     */
    public void fillVisaPaymentInfo(Checkout_POJO data) {
        Log.getLogger(getClass()).info("Filling Visa payment information");

        selectOption(CARD_TYPE, data.cardType);
        type(CARD_HOLDER, data.cardHolderName);
        type(CARD_NUMBER, data.cardNumber);
        selectOption(EXPIRE_MONTH, data.expireMonth);
        selectOption(EXPIRE_YEAR, data.expireYear);
        type(CARD_CODE, data.cardCode);
    }
}
