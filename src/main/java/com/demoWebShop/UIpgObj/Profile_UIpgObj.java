package com.demoWebShop.UIpgObj;

import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Profile_UIpgObj extends Base_UIPage {

    private static final String CUSTOMER_INFO_LINK = "a[href='/customer/info']";
    private static final String FIRST_NAME = "#FirstName";
    private static final String LAST_NAME = "#LastName";
    private static final String EMAIL_INPUT = "#Email";
    //private static final String SAVE_BUTTON = "button[name='save-info-button']";
    private static final String SAVE_BUTTON = "input[type='submit'][value='Save']";
    

    public Profile_UIpgObj(Page page) {
        super(page);
    }

    public void goToCustomerInfoPage() {
        Locator myAccount = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions().setName("My account")
        );

        click(myAccount, "Navigate to Customer Info page");
        Log.getLogger(getClass()).info("Navigate to Customer Info page (goToCustomerInfoPage).");
    }

    public void updateFirstName(String firstName) {
        type(FIRST_NAME, firstName);
        Log.getLogger(getClass()).info("Type first name into field: "+firstName+" (updateFirstName).");
    }

    public void updateLastName(String lastName) {
        type(LAST_NAME, lastName);
        Log.getLogger(getClass()).info("Type last name into field: "+lastName+" (updateLastName).");
    }

    public void updateEmail(String email) {
        type(EMAIL_INPUT, email);
        Log.getLogger(getClass()).info("Type email into field: "+email+" (updateEmail).");
    }

    public void saveProfile() {
        click(SAVE_BUTTON, "Save profile");
        Log.getLogger(getClass()).info("Click save button (saveProfile).");
    }
}
