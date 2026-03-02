package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Represents a generic product listing page (e.g., Books).
 */
public class ProductListing_UIpgObj extends Base_UIPage {

	private static final String PRODUCT_LINK = "a:has-text('%s')";
	private static final String ADD_TO_CART_BUTTON = "input[value='Add to cart']";

	
    private static final String FIRST_PRODUCT_ADD_TO_CART_BUTTON =
            "(//input[contains(@class,'add-to-cart-button')])[1]";

    public ProductListing_UIpgObj(Page page) {
        super(page);
    }

    public void addFirstProductToCart() {
        Log.getLogger(getClass()).info("Click button 'add first profuct to cart.'");  
        click(FIRST_PRODUCT_ADD_TO_CART_BUTTON, "First product add to cart button");
    }

    public void addSpecificProductToCart(String productName) {
        Log.getLogger(getClass()).info("Adding product to cart: " + productName);

        // Click the exact product link (strict-mode safe)
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                .setName(productName)
                .setExact(true))
            .click();

        // Click Add to Cart on the product details page
        page.locator("input[value='Add to cart']").click();
    }
   
    
}
