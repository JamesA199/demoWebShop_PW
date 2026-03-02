package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object representing the "My Account → Orders" page.
 *
 * This class provides:
 *  - A stable way to count how many orders the user has
 *  - A way to extract all order numbers from the order list
 *  - A helper to check if a specific order number exists
 *
 * DOM STRUCTURE :
 *
 * <div class="order-list">
 *     <div class="order-item">
 *         <div class="title">
 *             <strong>Order Number: 2229863</strong>
 *         </div>
 *         <ul class="info">
 *             <li>Order status: Pending</li>
 *             <li>Order Date: ...</li>
 *             <li>Order Total: ...</li>
 *         </ul>
 *         <div class="buttons">
 *             <input type="button" class="order-details-button" ...>
 *         </div>
 *     </div>
 * </div>
 *
 * IMPORTANT:
 *  - There is NO ".order-number" class in this DOM.
 *  - The order number is inside ".title strong".
 *  - The text format is: "Order Number: 2229863"
 */
public class OrderHistory_UIpgObj extends Base_UIPage {

    /**
     * Root selector for each order entry.
     * Each order is wrapped in a <div class="order-item">.
     *
     * This selector is stable because:
     *  - It is used consistently across the Demo Web Shop application.
     *  - It uniquely identifies each order block.
     */
    private static final String ORDER_ITEMS = ".order-list .order-item";

    /**
     * Selector for the <strong> element containing the order number.
     *
     * Example:
     * <strong>Order Number: 2229863</strong>
     *
     * We intentionally do NOT use text-based selectors here because:
     *  - The text contains dynamic numbers.
     *  - The structure is consistent and predictable.
     */
    private static final String ORDER_NUMBER_STRONG = ".title strong";

    public OrderHistory_UIpgObj(Page page) {
        super(page);
    }

    /**
     * Returns how many orders exist in the user's account.
     *
     * Uses Playwright's count() which:
     *  - Does NOT wait for visibility
     *  - Simply counts matching DOM nodes
     *
     * @return number of orders displayed
     */
    public int getOrderCount() {
        AllureUtils.addStep("Total orders: "+page.locator(ORDER_ITEMS).count());
        Log.getLogger(getClass()).info("Total orders: "+page.locator(ORDER_ITEMS).count());
        return page.locator(ORDER_ITEMS).count();
    }

    /**
     * Extracts all order numbers from the order list.
     *
     * Steps:
     *  1. Locate all ".order-item" blocks
     *  2. For each block, find ".title strong"
     *  3. Extract inner text (e.g., "Order Number: 2229863")
     *  4. Strip the prefix "Order Number:" to isolate the numeric ID
     *
     * @return List of order numbers as strings
     */
    public List<String> getAllOrderNumbers() {
        List<String> numbers = new ArrayList<>();

        Locator items = page.locator(ORDER_ITEMS);

        for (int i = 0; i < items.count(); i++) {

            // Extract raw text from <strong>
            String raw = items.nth(i)
                    .locator(ORDER_NUMBER_STRONG)
                    .innerText()
                    .trim();

            // raw example: "Order Number: 2229863"
            String number = raw.replace("Order Number:", "").trim();

            numbers.add(number);
        }

        return numbers;
    }

    /**
     * Checks whether a specific order number exists in the user's order history.
     *
     * This is useful for:
     *  - End-to-end checkout validation
     *  - Ensuring the order appears in the account after purchase
     *
     * @param orderNumber the order number to search for
     * @return true if the order exists, false otherwise
     */
    public boolean hasOrder(String orderNumber) {
        AllureUtils.addStep("Found order number: "+orderNumber);
        Log.getLogger(getClass()).info("Found order number: "+orderNumber);
        return getAllOrderNumbers().contains(orderNumber);
    }
}
