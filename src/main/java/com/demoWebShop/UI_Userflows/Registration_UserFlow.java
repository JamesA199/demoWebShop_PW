package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.*;
import com.demoWebShop.Utilities.Log;
import com.demoWebShop.Utilities.TestData;
import com.microsoft.playwright.Page;

public class Registration_UserFlow {

    private final Page page;
    private final HomePage_UIpgObj homePage;
    private final Register_UIpgObj registerPage;
    private final RegisterResult_UIpgObj registerResultPage;

    public Registration_UserFlow(Page page) {
        this.page = page;
        this.homePage = new HomePage_UIpgObj(page);
        this.registerPage = new Register_UIpgObj(page);
        this.registerResultPage = new RegisterResult_UIpgObj(page);
    }

    public void navigateToRegister() {
        Log.getLogger(getClass()).info("Click Register button (navigateToRegister)");
        homePage.clickRegister();
    }

    // ⭐ New overload (no args)
    public void registerNewUser() {
        String firstName = TestData.getFirstName();
        String lastName  = TestData.getLastName();
        String email     = TestData.generateUniqueEmail();
        String password  = TestData.getDefaultPassword();

        // if you need it later for loginWithRegisteredUser()
        TestData.setLastRegisteredEmail(email);

        registerNewUser(firstName, lastName, email, password);
    }

    // Existing method
    public void registerNewUser(String firstName, String lastName,
                                String email, String password) {
        Log.getLogger(getClass()).info("In method 'registerNewUser'.");
        navigateToRegister();
        registerPage.register(firstName, lastName, email, password);
        Log.getLogger(getClass()).info("Finished registering new user.");
        //registerResultPage.clickContinue();
    }

    public String getRegistrationResultMessage() {
        return registerResultPage.getResultMessage();
    }

    public void continueAfterRegistration() {
        registerResultPage.clickContinue();
        Log.getLogger(getClass()).info("Click 'Continue' button (continueAfterRegistration).");
    }
}
