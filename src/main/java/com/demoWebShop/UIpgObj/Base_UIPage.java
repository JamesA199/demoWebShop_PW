package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Base_UIPage {

    protected final Page page;

    public Base_UIPage(Page page) {
        this.page = page;
    }

    // ------------------------------------------------------------
    // CLICK HELPERS
    // ------------------------------------------------------------

    /** Click using a CSS selector (delegates to Locator version). */
    protected void click(String selector, String description) {
        click(page.locator(selector), description);
    }

    /** Stable Playwright click with visibility wait. */
    protected void click(Locator locator, String description) {
        AllureUtils.addStep("Click: " + description);
        Log.getLogger(this.getClass()).info("Click: " + description);

        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        locator.click(new Locator.ClickOptions().setTimeout(8000));
    }

    // ------------------------------------------------------------
    // CLICK + NAVIGATION
    // ------------------------------------------------------------

    protected void clickAndWaitForNavigation(Locator locator, String description) {
        AllureUtils.addStep("Click and wait for navigation: " + description);
        Log.getLogger(getClass()).info("Click and wait for navigation: " + description);

        page.waitForNavigation(() -> click(locator, description));
    }

    // ------------------------------------------------------------
    // CLICK + AJAX
    // ------------------------------------------------------------

    protected void clickAndWaitForAjax(Locator locator, String description) {
        AllureUtils.addStep("Click and wait for AJAX: " + description);
        click(locator, description);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        Log.getLogger(getClass()).info("Click and wait for AJAX: " + description);
    }

    /** Overload for String selector */
    protected void clickAndWaitForAjax(String selector, String description) {
        clickAndWaitForAjax(page.locator(selector), description);
    }

    // ------------------------------------------------------------
    // INPUT HELPERS
    // ------------------------------------------------------------

    protected void type(String selector, String value) {
        if (value == null) value = "";

        Locator input = page.locator(selector);
        input.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        input.fill(value);

        AllureUtils.addStep("Type '" + value + "' into: " + selector);
        Log.getLogger(getClass()).info("Type '" + value + "' into: " + selector);
    }

    protected void selectOption(String selector, String value) {
        if (value == null || value.isEmpty()) return;

        Locator dropdown = page.locator(selector);
        dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        dropdown.selectOption(value);

        AllureUtils.addStep("Select option '" + value + "' from: " + selector);
        Log.getLogger(getClass()).info("Select option '" + value + "' from: " + selector);
    }

    protected void selectRadioOption(String selector, String description) {
        Locator radio = page.locator(selector);
        radio.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        radio.check();

        AllureUtils.addStep("Selected radio: " + description);
        Log.getLogger(getClass()).info("Selected radio: " + description);
    }

    // ------------------------------------------------------------
    // TEXT + VISIBILITY
    // ------------------------------------------------------------

    protected String getText(String selector) {
        return page.locator(selector).innerText().trim();
    }

    protected String getText(Locator locator) {
        AllureUtils.addStep("Retrieved text from locator: "+locator+", "+locator.innerText().trim());
        Log.getLogger(getClass()).info("Retrieved text from locator: "+locator+", "+locator.innerText().trim());
        return locator.innerText().trim();
    }

    protected boolean isVisible(String selector) {
        AllureUtils.addStep(selector+" Is visible (isVisible).");
        Log.getLogger(getClass()).info(selector+" Is visible (isVisible).");
        return page.locator(selector).isVisible();
    }

    // ------------------------------------------------------------
    // WAIT HELPERS
    // ------------------------------------------------------------

    protected void waitForStep(String stepHeaderText) {
        page.waitForSelector("h2:has-text('" + stepHeaderText + "')");

        AllureUtils.addStep("waitForStep: " + stepHeaderText);
        Log.getLogger(getClass()).info("waitForStep: " + stepHeaderText);
    }

    protected void waitForSpinnerToDisappear() {
        page.waitForSelector(".loading",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED));

        AllureUtils.addStep("wait For Spinner To Disappear...");
        Log.getLogger(getClass()).info("wait For Spinner To Disappear...");
    }

    protected void smartStepWait() {
        page.waitForTimeout(300);

        AllureUtils.addStep("smartStepWait");
        Log.getLogger(getClass()).info("smartStepWait");
    }

    // ------------------------------------------------------------
    // RETRY CLICK HELPERS
    // ------------------------------------------------------------

    /** Retry click using a Locator */
    protected void retryClick(Locator locator, String description) {
        AllureUtils.addStep("Retry Click: " + description);
        Log.getLogger(this.getClass()).info("Retry Click: " + description);

        for (int i = 1; i <= 3; i++) {
            try {
                locator.waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
                locator.click();
                return;
            } catch (Exception e) {
                if (i == 3) throw e;
                page.waitForTimeout(300);
            }
        }
    }

    /** Retry click using a String selector */
    protected void retryClick(String selector, String description) {
        retryClick(page.locator(selector), description);
    }
}
