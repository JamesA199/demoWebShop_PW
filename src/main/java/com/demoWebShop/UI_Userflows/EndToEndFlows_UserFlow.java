package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.*;
import com.demoWebShop.Utilities.TestData;
import com.microsoft.playwright.Page;

/**
 * High-level orchestration layer for full end-to-end flows.
 *
 * This class coordinates:
 *  - Registration
 *  - Login
 *  - Profile update
 *  - Checkout flow
 *
 * All UserFlows are exposed as public fields for easy access in tests.
 * This matches the POJO-driven, readability-first architecture.
 */
public class EndToEndFlows_UserFlow extends Base_UFPage {

    // Public flows (Option 1 — Recommended)
    public Registration_UserFlow registrationFlow;
    public Login_UserFlow loginFlow;
    public ProfileUpdate_UserFlow profileFlow;
    public Checkout_UserFlow checkoutFlow;
    public Header_UserFlow headerFlow;
    
    public EndToEndFlows_UserFlow(Page page) {
        super(page);

        // Initialize all flows
        this.registrationFlow = new Registration_UserFlow(page);
        this.loginFlow = new Login_UserFlow(page);
        this.profileFlow = new ProfileUpdate_UserFlow(page);
        this.checkoutFlow = new Checkout_UserFlow(page);
        this.headerFlow = new Header_UserFlow(page);     
    }

    /**
     * Full flow: register → login → update profile
     */
    /*public void registerLoginAndUpdateProfile() {
        registrationFlow.registerNewUser();   // registers + stores email
        registrationFlow.continueAfterRegistration(); // clicks Continue
        headerFlow.logout();                  // logs out
        loginFlow.loginWithRegisteredUser();  // logs in with stored email
        profileFlow.updateProfile();          // updates profile
    }*/
    
    public void registerLoginAndUpdateProfile() { 
    	// 1. Register new user 
    	registrationFlow.registerNewUser(); 
    	registrationFlow.continueAfterRegistration(); 
    	// 2. Logout 
    	headerFlow.logout(); 
    	// 3. Login with the newly registered user 
    	loginFlow.loginWithRegisteredUser(); 
    	// 4. Update profile 
    	profileFlow.updateProfile( "UpdatedFirst", "UpdatedLast", TestData.getLastRegisteredEmail() ); 
    }
    
    /**
     * Full flow: login → add item → checkout
     */
    public void loginAddItemAndCheckout(String email, String password) {
        loginFlow.login(email, password);
        // Checkout is handled by Checkout_UserFlow
    }
}
