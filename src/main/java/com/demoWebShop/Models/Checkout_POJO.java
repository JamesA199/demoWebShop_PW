package com.demoWebShop.Models;

/**
 * Data model for the entire checkout process.
 *
 * This POJO contains:
 *  - Billing address data
 *  - Shipping address data
 *  - Shipping method selector
 *  - Payment method selector
 *
 * All fields are public by design — this is a pure data container
 * used by UserFlows and Page Objects. No business logic lives here.
 */
public class Checkout_POJO {

    // -----------------------------
    // Address POJOs
    // -----------------------------
    public Address_POJO billingAddress;
    public Address_POJO shippingAddress;
 
    
    // ⭐ NEW — Visa payment fields
    public String cardType;        // "Visa"
    public String cardHolderName;
    public String cardNumber;
    public String expireMonth;     // "01"
    public String expireYear;      // "2027"
    public String cardCode;        // "860"
    // -----------------------------
    // Method selectors (CSS)
    // -----------------------------
    public String shippingMethodCss;
    public String paymentMethodCss;

    // -----------------------------
    // Builder-style setters
    // -----------------------------
    public Checkout_POJO setBillingAddress(Address_POJO billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public Checkout_POJO setShippingAddress(Address_POJO shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public Checkout_POJO setShippingMethodCss(String shippingMethodCss) {
        this.shippingMethodCss = shippingMethodCss;
        return this;
    }

    public Checkout_POJO setPaymentMethodCss(String paymentMethodCss) {
        this.paymentMethodCss = paymentMethodCss;
        return this;
    }
   
}
