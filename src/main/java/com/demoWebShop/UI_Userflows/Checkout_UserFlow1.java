package com.demoWebShop.UI_Userflows;

import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.UIpgObj.*;
import com.microsoft.playwright.Page;

/**
 * High-level business flow for checkout.
 *
 * Handles:
 *  - Navigating to Books
 *  - Adding first product
 *  - Going to cart
 *  - Proceeding to checkout
 *  - Completing checkout using POJO data
 *  - Returning to home page
 *  - Navigating to My Account → Orders
 *  - Verifying order exists
 */
public class Checkout_UserFlow1 extends Base_UFPage {

    private final NavigationBar_UIpgObj nav;
    private final ProductListing_UIpgObj productListing;
    private final Cart_UIpgObj cart;
    private final Checkout_UIpgObj checkout;
    private final Profile_UIpgObj profile;

    public Checkout_UserFlow1(Page page) {
        super(page);
        this.nav = new NavigationBar_UIpgObj(page);
        this.productListing = new ProductListing_UIpgObj(page);
        this.cart = new Cart_UIpgObj(page);
        this.checkout = new Checkout_UIpgObj(page);
        this.profile = new Profile_UIpgObj(page);
    }

    /**
     * Full flow:
     * 1. Navigate to Books
     * 2. Add first product
     * 3. Go to cart
     * 4. Proceed to checkout
     * 5. Complete checkout
     * 6. Verify order success
     * 7. Return to home page
     * 8. Go to My Account → Orders
     * 9. Verify order exists
     */
    public void addBookAndCheckout(Checkout_POJO data) {

        // ------------------------------------------------------------
        // ADD ITEM TO CART
        // ------------------------------------------------------------
        nav.goToBooks();
        productListing.addFirstProductToCart();
        nav.goToCart();
        cart.proceedToCheckout();

        // ------------------------------------------------------------
        // COMPLETE CHECKOUT
        // ------------------------------------------------------------
        checkout.completeCheckoutWithDefaults(data);

        // ------------------------------------------------------------
        // VERIFY ORDER SUCCESS PAGE
        // ------------------------------------------------------------
        if (!checkout.isOrderCompleted()) {
            throw new AssertionError("Order did not complete successfully.");
        }

        // ------------------------------------------------------------
        // CLICK CONTINUE → RETURN TO HOME PAGE
        // ------------------------------------------------------------
        page.click("input.button-1.order-completed-continue-button");

        // ------------------------------------------------------------
        // NAVIGATE TO MY ACCOUNT
        // ------------------------------------------------------------
        page.click("a.account");  // email link top-right

        // ------------------------------------------------------------
        // CLICK ORDERS
        // ------------------------------------------------------------
        page.click("a[href='/order/history']");

        // ------------------------------------------------------------
        // VERIFY ORDER EXISTS
        // ------------------------------------------------------------
        boolean hasOrder = page.locator(".order-list .order-item").count() > 0;

        if (!hasOrder) {
            throw new AssertionError("No orders found in order history.");
        }
    }

    public boolean isOrderCompleted() {
        return checkout.isOrderCompleted();
    }
     
}
