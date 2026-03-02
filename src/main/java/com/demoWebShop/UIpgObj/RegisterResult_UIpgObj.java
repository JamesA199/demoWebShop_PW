package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

/**
 * Represents the registration result page.
 */
public class RegisterResult_UIpgObj extends Base_UIPage {

    private static final String RESULT_MESSAGE = "div.result";
    private static final String CONTINUE_BUTTON = "input.button-1.register-continue-button";

    public RegisterResult_UIpgObj(Page page) {
        super(page);
    }

    public String getResultMessage() {
        return getText(RESULT_MESSAGE);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON, "click continue button");
    }
}