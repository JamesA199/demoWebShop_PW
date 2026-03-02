package com.demoWebShop.Tests.UI;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.Models.Address_POJO;
import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.UI_Userflows.Account_UserFlow;
import com.demoWebShop.UI_Userflows.Checkout_UserFlow;
import com.demoWebShop.UI_Userflows.EndToEndFlows_UserFlow;
import com.demoWebShop.UI_Userflows.Login_UserFlow;
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
public class EndToEndUI_Tests_DD extends BaseTest {

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

	@Test(dataProvider = "checkoutData", dataProviderClass = ExcelDataProvider.class,
		    description = "Login → Add item to cart → Checkout (Data-Driven)")
	public void loginAddItemCheckout(String email, String password, String product) throws InterruptedException {

	    Log.getLogger(getClass()).info("*** Begin: Login → Add Item → Checkout (DD) ***");
        EndToEndFlows_UserFlow flow = new EndToEndFlows_UserFlow(getPage());
        String email1 = TestData.generateUniqueEmail();
        
        // Register the user
        flow.registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email1,
                password
        );
        // Logout to simulate a fresh login
        getPage().click("a[href='/logout']");
        Log.getLogger(getClass()).info("Logged out after registration.");
        
	    // ------------------------------------------------------------
	    // LOGIN
	    // ------------------------------------------------------------
	    Login_UserFlow login = new Login_UserFlow(page);
	    login.login(email1, password);

	    // ------------------------------------------------------------
	    // ADD PRODUCT + CHECKOUT (DATA‑DRIVEN PRODUCT)
	    // ------------------------------------------------------------
	    Checkout_UserFlow checkout = new Checkout_UserFlow(page);
	    checkout.addBookAndCheckout(product);

	    // ------------------------------------------------------------
	    // BUILD CHECKOUT DATA USING POJOs
	    // ------------------------------------------------------------
	    Checkout_POJO checkoutData = TestData.buildCheckoutData();

	    // Proceed to Confirm Order page
	    EndToEndFlows_UserFlow OrdPgflow = new EndToEndFlows_UserFlow(getPage());
	    ConfirmOrder_UIpgObj confirmPage = OrdPgflow.checkoutFlow.addBookAndCheckout(checkoutData);

	    // ------------------------------------------------------------
	    // ASSERTIONS ON CONFIRM ORDER PAGE
	    // ------------------------------------------------------------
	    Assert.assertTrue(confirmPage.getProductName().contains(product),
	            "Product name should match the data-driven product");

	    Assert.assertEquals(confirmPage.getProductQty(), "1");
	    Assert.assertTrue(confirmPage.getPaymentMethod().contains("Credit Card"));
	    Assert.assertTrue(confirmPage.getShippingMethod().contains("Ground"));

	    // ------------------------------------------------------------
	    // CONFIRM ORDER → THANK YOU PAGE
	    // ------------------------------------------------------------
	    ThankYou_UIpgObj thankYou = confirmPage.confirmOrder();
	    String orderNumber = thankYou.getOrderNumber();

	    Assert.assertTrue(thankYou.isSuccessMessageVisible(), "Success message should be visible");
	    Assert.assertTrue(orderNumber.matches("\\d+"), "Order number should be numeric");

	    thankYou.clickContinue();

	    // ------------------------------------------------------------
	    // NAVIGATE TO ACCOUNT → ORDERS
	    // ------------------------------------------------------------
	    Account_UserFlow accountFlow = new Account_UserFlow(getPage());
	    OrderHistory_UIpgObj orders = accountFlow.goToOrderHistory();

	    // Validate order count
	    AssertionUtils.assertOrderCount(orders.getOrderCount(), 1, getClass());

	    // Validate order exists
	    AssertionUtils.assertOrderExists(orders, orderNumber, getClass());

	    Log.getLogger(getClass()).info("*** End: Login → Add Item → Checkout (DD) ***");
	}
}

