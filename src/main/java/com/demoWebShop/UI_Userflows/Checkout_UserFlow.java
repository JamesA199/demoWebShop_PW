package com.demoWebShop.UI_Userflows;

import com.demoWebShop.Models.Checkout_POJO;
import com.demoWebShop.UIpgObj.Cart_UIpgObj;
import com.demoWebShop.UIpgObj.Checkout_UIpgObj;
import com.demoWebShop.UIpgObj.ConfirmOrder_UIpgObj;
import com.demoWebShop.UIpgObj.NavigationBar_UIpgObj;
import com.demoWebShop.UIpgObj.ProductListing_UIpgObj;
import com.demoWebShop.Utilities.TestData;
import com.microsoft.playwright.Page;

public class Checkout_UserFlow extends Base_UFPage {

    private final NavigationBar_UIpgObj nav;
    private final ProductListing_UIpgObj productListing;
    private final Cart_UIpgObj cart;
    private final Checkout_UIpgObj checkout;

    public Checkout_UserFlow(Page page) {
        super(page);
        this.nav = new NavigationBar_UIpgObj(page);
        this.productListing = new ProductListing_UIpgObj(page);
        this.cart = new Cart_UIpgObj(page);
        this.checkout = new Checkout_UIpgObj(page);
    }

    /**
     * Full flow using POJO-driven checkout data.
     */
    public ConfirmOrder_UIpgObj addBookAndCheckout(Checkout_POJO data) {

        // Navigate to Books and add first product
        nav.goToBooks();
        productListing.addFirstProductToCart();

        // Go to cart and begin checkout
        nav.goToCart();
        cart.proceedToCheckout();

        // Complete all checkout steps
        checkout.completeCheckoutWithDefaults(data);

        return new ConfirmOrder_UIpgObj(page);
    }

    /**
     * Overloaded flow: product name is data-driven (Excel),
     * checkout details still come from POJO.
     */
    public ConfirmOrder_UIpgObj addBookAndCheckout(String productName) {

        // Navigate to Books
        nav.goToBooks();

        // Add the specific product from Excel
        productListing.addSpecificProductToCart(productName);

        // Go to cart and begin checkout
        nav.goToCart();
        cart.proceedToCheckout();

        // Build default checkout data
        Checkout_POJO checkoutData = TestData.buildCheckoutData();

        // Complete checkout using POJO
        checkout.completeCheckoutWithDefaults(checkoutData);

        return new ConfirmOrder_UIpgObj(page);
    }
}
