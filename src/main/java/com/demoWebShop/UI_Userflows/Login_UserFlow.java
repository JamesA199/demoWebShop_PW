package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.Login_UIpgObj;
import com.demoWebShop.Utilities.Log;
import com.demoWebShop.Utilities.TestData;
import com.microsoft.playwright.Page;

public class Login_UserFlow extends Base_UFPage {

    private final Login_UIpgObj login;

    public Login_UserFlow(Page page) {
        super(page);
        this.login = new Login_UIpgObj(page);
    }

    // ⭐ Overload #1 — 2 arguments 
    public void login(String email, String password) { 
    	login(email, password, false); 
    }    
    
    /**
     * Full control login.
     */
    public void login(String email, String password, boolean rememberMe) {
        login.goToLoginPage();
        login.fillLoginForm(email, password, rememberMe);
        login.submitLogin();
    }

    /**
     * Login using the last registered user.
     */
    public void loginWithRegisteredUser() {
        Log.getLogger(getClass()).info("Login with registered user (loginWithRegisteredUser).");
        String email = TestData.getLastRegisteredEmail();
        String password = TestData.getDefaultPassword();

        login.goToLoginPage();   // ⭐ REQUIRED
        Log.getLogger(getClass()).info("Click login (goToLoginPage).");
        login.fillLoginForm(email, password, false);
        login.submitLogin();
    }    
    // ⭐ REQUIRED FOR YOUR TESTS 
    public String getLoginErrorMessage() { 
    	return login.getLoginErrorMessage(); 
    }    
}
