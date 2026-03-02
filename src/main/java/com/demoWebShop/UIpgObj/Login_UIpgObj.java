package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Login_UIpgObj extends Base_UIPage {

    private static final String LOGIN_LINK = "a[href='/login']";
    private static final String EMAIL_INPUT = "#Email";
    private static final String PASSWORD_INPUT = "#Password";
    private static final String REMEMBER_ME_CHECKBOX = "#RememberMe";

    // ⭐ Bulletproof login button selector
    //It is an <input> element.
    private static final String LOGIN_BUTTON = "input[type='submit'][value='Log in']";

    private static final String LOGIN_ERROR = ".validation-summary-errors li";   
    
    public Login_UIpgObj(Page page) {
        super(page);
    }

    public String getLoginErrorMessage() {
        return getText(LOGIN_ERROR);
    }
    
    
    public void goToLoginPage() {
        AllureUtils.addStep("Navigate to Login page");

        Locator loginLink = page.locator(LOGIN_LINK);

        loginLink.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        loginLink.click();

        page.waitForURL("**/login");
    }

    public void fillLoginForm(String email, String password, boolean rememberMe) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);

        if (rememberMe) {
            click(REMEMBER_ME_CHECKBOX, "Check Remember Me");
        }
    }

    public void submitLogin() {
    	System.out.println("URL BEFORE CLICKING LOGIN BUTTON: " + page.url());

        AllureUtils.addStep("Submitting Login form");
        Log.getLogger(getClass()).info("Submitting Login form (submitLogin).");
        click(LOGIN_BUTTON, "Click Login button");
    }
}
