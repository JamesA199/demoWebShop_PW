package com.demoWebShop.Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Centralized configuration loader.
 * Reads config.properties once and exposes values to the framework.
 */
public class ConfigManager {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getEnv() {
        return get("env");
    }

    public static String getVideoMode() {
        return get("videoMode");
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(get("screenshotOnFailure"));
    }

    public static int getTimeout() {
        return Integer.parseInt(get("timeout"));
    }
}
