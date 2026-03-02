package DataProviders;

import org.testng.annotations.DataProvider;

import com.demoWebShop.Utilities.ExcelUtils;

public class ExcelDataProvider {

    @DataProvider(name = "checkoutData")
    public Object[][] getCheckoutData() {

        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx", "Checkout");

        int rows = excel.getRowCount();
        Object[][] data = new Object[rows - 1][3]; // example: email, password, product

        for (int i = 1; i < rows; i++) {
            data[i - 1][0] = excel.getCellData(i, 0); // email
            data[i - 1][1] = excel.getCellData(i, 1); // password
            data[i - 1][2] = excel.getCellData(i, 2); // product
        }

        return data;
    }
}
