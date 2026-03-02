package com.demoWebShop.Utilities;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

    @DataProvider(name = "userData")
    public Object[][] userData() {
        var rows = CSVReader.readCSV("users.csv");
        Object[][] data = new Object[rows.size()][4];

        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i); // firstName, lastName, email, password
        }
        return data;
    }
}
