package com.demoWebShop.Utilities;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ExcelUtils {
	
	    private Sheet sheet;

	    public ExcelUtils(String filePath, String sheetName) {
	        try {
	            FileInputStream fis = new FileInputStream(filePath);
	            Workbook workbook = WorkbookFactory.create(fis);
	            sheet = workbook.getSheet(sheetName);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
	        }
	    }

	    public int getRowCount() {
	        return sheet.getPhysicalNumberOfRows();
	    }

	    public String getCellData(int row, int col) {
	        return sheet.getRow(row).getCell(col).toString();
	    }
	}
