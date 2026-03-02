package com.demoWebShop.Utilities;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.nio.charset.StandardCharsets;

/**
 * AllureUtils
 * Centralized Allure attachment helpers.
 */
public final class AllureUtils {

    private AllureUtils() {}

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(Page page) {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static byte[] attachText(String name, String content) {
        return content.getBytes(StandardCharsets.UTF_8);
    }

    public static void addStep(String message) {
        Allure.step(message);
    }
}
