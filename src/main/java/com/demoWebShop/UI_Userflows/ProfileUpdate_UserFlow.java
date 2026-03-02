package com.demoWebShop.UI_Userflows;

import com.demoWebShop.UIpgObj.Profile_UIpgObj;
import com.microsoft.playwright.Page;

public class ProfileUpdate_UserFlow extends Base_UFPage {

    public Profile_UIpgObj profile;

    public ProfileUpdate_UserFlow(Page page) {
        super(page);
        this.profile = new Profile_UIpgObj(page);
    }

    /**
     * Overload #1 — Full control (your choice B)
     */
    public void updateProfile(String firstName, String lastName, String email) {
        profile.goToCustomerInfoPage();
        profile.updateFirstName(firstName);
        profile.updateLastName(lastName);
        profile.updateEmail(email);
        profile.saveProfile();
    }

    /**
     * Overload #2 — Optional default version (if you still want it)
     */
    public void updateProfile() {
        profile.goToCustomerInfoPage();
        profile.updateFirstName("TestAuto");
        profile.updateLastName("User");
        profile.updateEmail("default@test.com");
        profile.saveProfile();
    }
}
