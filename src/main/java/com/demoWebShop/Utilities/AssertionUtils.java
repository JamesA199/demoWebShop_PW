package com.demoWebShop.Utilities;

import org.testng.Assert;
import com.demoWebShop.UIpgObj.OrderHistory_UIpgObj;

public class AssertionUtils {

    public static void assertOrderCount(int actual, int expected, Class<?> clazz) {
        if (actual != expected) {
            String msg = "Order count incorrect. Expected " + expected + " but found " + actual;
            AllureUtils.addStep(msg);
            Log.getLogger(clazz).error(msg);
            Assert.fail(msg);
        }

        String msg = "Correct order count = " + expected;
        AllureUtils.addStep(msg);
        Log.getLogger(clazz).info(msg);
    }
    
    /**
     * Asserts that a specific order number exists in the user's order history.
     *
     * @param ordersPage   The OrderHistory_UIpgObj instance
     * @param orderNumber  The order number to verify
     * @param clazz        The calling test class (for logging context)
     */
    public static void assertOrderExists(OrderHistory_UIpgObj ordersPage,
                                         	String orderNumber,
                                         	Class<?> clazz) {

        boolean exists = ordersPage.hasOrder(orderNumber);

        if (!exists) {
            String msg = "Order number " + orderNumber + " was NOT found in order history.";
            AllureUtils.addStep(msg);
            Log.getLogger(clazz).error(msg);
            Assert.fail(msg);
        }

        String msg = "Order number " + orderNumber + " exists in order history.";
        AllureUtils.addStep(msg);
        Log.getLogger(clazz).info(msg);
    }
   
}
