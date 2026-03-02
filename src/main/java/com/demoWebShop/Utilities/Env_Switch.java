package com.demoWebShop.Utilities;

/**
 * Simple environment switcher.
 * If you later add multiple base URLs per env, centralize that logic here.
 */
public class Env_Switch {

    public static String resolveBaseUrl() {
        // For now, just return the baseUrl from config.
        // You can extend this to map env -> URL.
        return ConfigManager.getBaseUrl();
    }
}
