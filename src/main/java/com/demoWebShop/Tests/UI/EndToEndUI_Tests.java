package com.demoWebShop.Tests.UI;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.Models.Address_POJO;
import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.UI_Userflows.Account_UserFlow;
import com.demoWebShop.UI_Userflows.EndToEndFlows_UserFlow;
import com.demoWebShop.UIpgObj.ConfirmOrder_UIpgObj;
import com.demoWebShop.UIpgObj.OrderHistory_UIpgObj;
import com.demoWebShop.UIpgObj.ThankYou_UIpgObj;
import com.demoWebShop.Utilities.TestData;

import DataProviders.ExcelDataProvider;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.AssertionUtils;
import com.demoWebShop.Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * End-to-end UI tests combining multiple business flows.
 * 
 * These tests validate:
 *  - Registration
 *  - Login
 *  - Profile update
 *  - Add item to cart
 *  - Full checkout using POJOs
 */
public class EndToEndUI_Tests extends BaseTest {

    @Test(description = "Register → Login → Update profile")
    public void registerLoginUpdateProfile() {

        Log.getLogger(getClass()).info("*** Begin: Register → Login → Update Profile ***");

        EndToEndFlows_UserFlow flow = new EndToEndFlows_UserFlow(getPage());
        flow.registerLoginAndUpdateProfile();

        Assert.assertTrue(
                getPage().url().contains("/customer/info"),
                "User should land on the Customer Info page after updating profile"
        );

        Log.getLogger(getClass()).info("*** End: Register → Login → Update Profile ***");
    }


    @Test(description = "Login → Add item to cart → Checkout")
    public void loginAddItemCheckout() throws InterruptedException {

        Log.getLogger(getClass()).info("*** Begin: Login → Add Item → Checkout ***");

        // ------------------------------------------------------------
        // PRECONDITION: Create a new user
        // ------------------------------------------------------------
        String email = TestData.generateUniqueEmail();
        String password = TestData.getDefaultPassword();

        EndToEndFlows_UserFlow flow = new EndToEndFlows_UserFlow(getPage());

        // Register the user
        flow.registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email,
                password
        );

        // Logout to simulate a fresh login
        getPage().click("a[href='/logout']");
        Log.getLogger(getClass()).info("Logged out after registration.");

        // Login again
        flow.loginFlow.login(email, password, false);


        // ------------------------------------------------------------
        // BUILD CHECKOUT DATA USING POJOs
        // ------------------------------------------------------------

        Checkout_POJO checkoutData = TestData.buildCheckoutData();

	    // Checkout until Confirm Order page
	    ConfirmOrder_UIpgObj confirmPage = flow.checkoutFlow.addBookAndCheckout(checkoutData);
		Log.getLogger(getClass()).info("checking asserts...");
	    // Assertions on Confirm Order page
	    Assert.assertTrue(confirmPage.getProductName().contains("Computing and Internet"));
	    Assert.assertEquals(confirmPage.getProductPrice(), "10.00");
	    Assert.assertEquals(confirmPage.getProductQty(), "1");
	    Assert.assertEquals(confirmPage.getProductTotal(), "10.00");
	    Assert.assertTrue(confirmPage.getPaymentMethod().contains("Credit Card"));
	    Assert.assertTrue(confirmPage.getShippingMethod().contains("Ground"));

	    // Confirm the order → Thank You page
	    ThankYou_UIpgObj thankYou = confirmPage.confirmOrder();
	    String orderNumber = thankYou.getOrderNumber();
	    
	    Assert.assertTrue(thankYou.isSuccessMessageVisible(), "Order success message should be visible");
	    Assert.assertTrue(orderNumber.matches("\\d+"), "Order number should be numeric");
	    
	    // Click Continue → Home Page
	    thankYou.clickContinue();

	    // Navigate to My Account → Orders
	    Account_UserFlow accountFlow = new Account_UserFlow(getPage());
	    OrderHistory_UIpgObj orders = accountFlow.goToOrderHistory();

	    int count = orders.getOrderCount();
	    AssertionUtils.assertOrderCount(count, 1, getClass());
	    
	    // Verify the order exists
	    /*Assert.assertTrue(
	            orders.hasOrder(orderNumber),
	            "Order number " + orderNumber + " should exist in order history"
	    );*/

	    AssertionUtils.assertOrderExists(orders, orderNumber, getClass());
	    
        Log.getLogger(getClass()).info("*** End: Login → Add Item → Checkout ***");
    }
}
