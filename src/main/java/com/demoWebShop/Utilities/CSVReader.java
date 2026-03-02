package com.demoWebShop.Utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple CSV reader for loading test users from users.csv.
 * Returns each row as a String[].
 */
public class CSVReader {

    public static List<String[]> readCSV(String fileName) {
        List<String[]> rows = new ArrayList<>();

        try {
            InputStream is = CSVReader.class.getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new RuntimeException("CSV file not found: " + fileName);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                rows.add(line.split(","));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV: " + fileName, e);
        }

        return rows;
    }
}
