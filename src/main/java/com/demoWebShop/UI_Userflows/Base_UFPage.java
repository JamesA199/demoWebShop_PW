/*These classes represent business workflows, not individual pages.
Examples:
Login_UserFlow
Registration_UserFlow
Checkout_UserFlow
EndToEndFlows_UserFlow

These classes orchestrate:
multiple page objects
multi-step business logic
cross-page flows

Their base class is:
BasePage
This class contains:
retry click
radio selection
simple helpers
These are flow-level utilities.*/

package com.demoWebShop.UI_Userflows;

import com.demoWebShop.Utilities.AllureUtils;
import com.microsoft.playwright.*;

public class Base_UFPage {

    protected Page page;

    public Base_UFPage(Page page) {
        this.page = page;
    }

    /**
     * Retries clicking a locator up to 3 times.
     * Helps with flaky demo-site buttons.
     */
    public void retryClick(Locator locator, String description) {
        int attempts = 3;
        for (int i = 1; i <= attempts; i++) {
            try {
                locator.click(new Locator.ClickOptions().setTimeout(5000));
                AllureUtils.addStep("Clicked: " + description);
                return;
            } catch (Exception e) {
                if (i == attempts) throw e;
                page.waitForTimeout(500);
            }
        }
    }
    /**
     * Selects a radio button by CSS selector.
     */
    public void selectRadioOption(String cssSelector, String description) {
        Locator radio = page.locator(cssSelector);
        radio.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        radio.check();
        AllureUtils.addStep("Selected radio option: " + description);
    }
    
}