package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;

/**
 * Represents the registration page.
 */
public class Register_UIpgObj extends Base_UIPage {

    private static final String GENDER_MALE = "input#gender-male";
    private static final String FIRST_NAME = "input#FirstName";
    private static final String LAST_NAME = "input#LastName";
    private static final String EMAIL = "input#Email";
    private static final String PASSWORD = "input#Password";
    private static final String CONFIRM_PASSWORD = "input#ConfirmPassword";
    private static final String REGISTER_BUTTON = "input#register-button";

    public Register_UIpgObj(Page page) {
        super(page);
    }

    public void register(String firstName, String lastName, String email, String password) {
    	Log.getLogger(getClass()).info("Add new user details...");
        click(GENDER_MALE, "select gender make");
        Log.getLogger(getClass()).info("Select gender 'male'.");
        type(FIRST_NAME, firstName);
        Log.getLogger(getClass()).info("Enter first name: "+firstName);
        type(LAST_NAME, lastName);
        Log.getLogger(getClass()).info("Enter last name: "+lastName);
        type(EMAIL, email);
        Log.getLogger(getClass()).info("Enter email: "+email);
        type(PASSWORD, password);
        Log.getLogger(getClass()).info("Enter password: "+password);
        type(CONFIRM_PASSWORD, password);
        click(REGISTER_BUTTON, "Click register button");
        Log.getLogger(getClass()).info("Click register button.");
    }
}
