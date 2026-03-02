package com.demoWebShop.Utilities;

import com.demoWebShop.Models.Address_POJO;
import com.demoWebShop.Models.Checkout_POJO;

import java.util.UUID;

/**
 * Centralized test data builder for all POJOs.
 *
 * This class generates:
 *  - Unique emails
 *  - Default names
 *  - Default passwords
 *  - Billing Address POJO
 *  - Shipping Address POJO
 *  - Full Checkout_POJO including Visa payment info
 *
 * Keeping all data creation here ensures:
 *  - Clean tests
 *  - Reusable data builders
 *  - Consistent values across flows
 */
public class TestData {

    // ------------------------------------------------------------
    // BASIC USER INFO
    // ------------------------------------------------------------

    /** Generates a unique email for registration. */
    public static String generateUniqueEmail() {
        return "auto_" + UUID.randomUUID() + "@example.com";
    }

    /** Default first name for test users. */
    public static String getFirstName() {
        return "TestAuto";
    }

    /** Default last name for test users. */
    public static String getLastName() {
        return "User";
    }

    /** Default password for all test accounts. */
    public static String getDefaultPassword() {
        return "Password123!";
    }

	 /**
	  * Returns the last registered email.
	  *
	  * If no email has been stored yet, this method throws an exception
	  * to prevent silent failures.
	  */
	 public static String getLastRegisteredEmail() {
	     if (lastRegisteredEmail == null) {
	         throw new IllegalStateException(
	                 "No registered email found. Did you forget to call setLastRegisteredEmail()?"
	         );
	     }
	     return lastRegisteredEmail;
	 }    
    
    
    // ------------------------------------------------------------
    // ADDRESS BUILDERS
    // ------------------------------------------------------------
    public static Address_POJO buildBillingAddress() {
        return new Address_POJO()
                .setFirstName("TestAuto")
                .setLastName("User")
                .setEmail("billing@test.com")
                .setCompany("")          // ⭐ ADD THIS
                .setCountry("United States")
                .setState("Alabama")
                .setCity("New York")
                .setAddress1("123 Billing St")
                .setAddress2("")         // ⭐ ADD THIS
                .setZip("10001")
                .setPhone("5551234567")
                .setFax("");             // ⭐ ADD THIS
    }
    
    
    /** Builds a Shipping Address POJO with default values. */
    public static Address_POJO buildShippingAddress() {
        return new Address_POJO()
                .setFirstName("TestAuto")
                .setLastName("User")
                .setEmail("shipping@test.com")
                .setCompany("")          // ⭐ ADD THIS
                .setCountry("United States")
                .setState("Alabama")
                .setCity("New York")
                .setAddress1("123 Shipping Ave")
                .setAddress2("")         // ⭐ ADD THIS
                .setZip("10001")
                .setPhone("5559876543")
                .setFax("");             // ⭐ ADD THIS
    }

    // ------------------------------------------------------------
    // FULL CHECKOUT DATA BUILDER
    // ------------------------------------------------------------

    /**
     * Builds the full Checkout_POJO including:
     *  - Billing Address
     *  - Shipping Address
     *  - Shipping Method
     *  - Payment Method
     *  - Visa Payment Info
     */
    public static Checkout_POJO buildCheckoutData() {
        Checkout_POJO data = new Checkout_POJO();

        data.billingAddress = buildBillingAddress();
        data.shippingAddress = buildShippingAddress();

        // Shipping + Payment method selectors
        data.shippingMethodCss = "input#shippingoption_0"; // Ground
        data.paymentMethodCss = "input#paymentmethod_1";   // Credit Card

        // Visa fields
        data.cardType = "Visa";
        data.cardHolderName = "RAINA LEANNON";
        data.cardNumber = "4818965900482611";
        data.expireMonth = "10";
        data.expireYear = "2027";
        data.cardCode = "860";

        return data;
    }
    
	 // ------------------------------------------------------------
	 // LAST REGISTERED EMAIL STORAGE
	 // ------------------------------------------------------------
	
	 /**
	  * Stores the last registered email so that other flows
	  * (like login or profile update) can retrieve it later.
	  *
	  * This is intentionally static because TestData acts as a
	  * global test data provider for the entire test run.
	  */
	 private static String lastRegisteredEmail;
	
	 /**
	  * Saves the last registered email.
	  */
	 public static void setLastRegisteredEmail(String email) {
	     lastRegisteredEmail = email;
	 }
	
}
