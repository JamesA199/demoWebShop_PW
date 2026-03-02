package com.demoWebShop.Tests.UI;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.UI_Userflows.Registration_UserFlow;
import com.demoWebShop.Utilities.UserDataProvider;
public class registerUsersFrom_DD extends BaseTest {
@Test(
    description = "Register users from CSV",
    dataProvider = "userData",
    dataProviderClass = UserDataProvider.class,
    groups = {"functional"}
)
public void registerUsersFromCSV(String firstName, String lastName, String email, String password) {
    Registration_UserFlow reg = new Registration_UserFlow(getPage());
    reg.registerNewUser(firstName, lastName, email, password);

	    Assert.assertTrue(
	        reg.getRegistrationResultMessage().toLowerCase().contains("completed"),
	        "Registration should be completed"
	    );
	}
}
