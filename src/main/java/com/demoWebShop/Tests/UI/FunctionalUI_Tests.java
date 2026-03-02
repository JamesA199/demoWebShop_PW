package com.demoWebShop.Tests.UI;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.UI_Userflows.Login_UserFlow;
import com.demoWebShop.UI_Userflows.Registration_UserFlow;
import com.demoWebShop.UI_Userflows.ProfileUpdate_UserFlow;
import com.demoWebShop.Utilities.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Functional tests: registration, login, profile update, logout.
 */
public class FunctionalUI_Tests extends BaseTest {

    @Test(description = "Register a new user")
    public void registerNewUser() {
        String email = TestData.generateUniqueEmail();
        String password = TestData.getDefaultPassword();

        Registration_UserFlow registrationFlow = new Registration_UserFlow(getPage());
        registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email,
                password
        );

        String resultMessage = registrationFlow.getRegistrationResultMessage();
        Assert.assertTrue(resultMessage.toLowerCase().contains("completed"),
                "Registration should be completed");
    }

    @Test(description = "Login with valid credentials")
    public void loginWithValidCredentials() {
        // Precondition: register a user first
        String email = TestData.generateUniqueEmail();
        String password = TestData.getDefaultPassword();

        Registration_UserFlow registrationFlow = new Registration_UserFlow(getPage());
        registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email,
                password
        );
        registrationFlow.continueAfterRegistration();

        // Now login
        Login_UserFlow loginFlow = new Login_UserFlow(getPage());
        loginFlow.login(email, password, false);

        Assert.assertTrue(getPage().locator("a.account").isVisible(),
                "Account link should be visible after login");
    }

    @Test(description = "Login with invalid credentials")
    public void loginWithInvalidCredentials() {
        Login_UserFlow loginFlow = new Login_UserFlow(getPage());
        loginFlow.login("invalid@example.com", "WrongPassword", false);

        String error = loginFlow.getLoginErrorMessage();
        Assert.assertTrue(error.toLowerCase().contains("login was unsuccessful"),
                "Error message should indicate unsuccessful login");
    }

    @Test(description = "Update customer info")
    public void updateCustomerInfo() {
        String email = TestData.generateUniqueEmail();
        String password = TestData.getDefaultPassword();

        Registration_UserFlow registrationFlow = new Registration_UserFlow(getPage());
        registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email,
                password
        );
        registrationFlow.continueAfterRegistration();

        ProfileUpdate_UserFlow profileFlow = new ProfileUpdate_UserFlow(getPage());
        profileFlow.updateProfile("UpdatedFirst", "UpdatedLast", email);

        // Simple assertion: page should still show customer info form
        Assert.assertTrue(getPage().url().contains("/customer/info"),
                "Should remain on customer info page after update");
    }

    @Test(description = "Logout")
    public void logout() {
        String email = TestData.generateUniqueEmail();
        String password = TestData.getDefaultPassword();

        Registration_UserFlow registrationFlow = new Registration_UserFlow(getPage());
        registrationFlow.registerNewUser(
                TestData.getFirstName(),
                TestData.getLastName(),
                email,
                password
        );
        registrationFlow.continueAfterRegistration();

        // Logout via header link
        getPage().click("a[href='/logout']");
        Assert.assertTrue(getPage().locator("a[href='/login']").isVisible(),
                "Login link should be visible after logout");
    }
}
