package com.demoWebShop.Utilities;

/**
 * LogUtils
 * Simple centralized logging utility.
 */
public final class LogUtils {

    private LogUtils() {}

    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public static void error(String message, Throwable t) {
        System.err.println("[ERROR] " + message);
        if (t != null) {
            t.printStackTrace();
        }
    }
}
