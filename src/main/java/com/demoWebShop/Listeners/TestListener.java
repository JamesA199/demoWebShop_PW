package com.demoWebShop.Listeners;

import com.demoWebShop.Base.BaseTest;
import com.demoWebShop.Utilities.ConfigManager;
import com.demoWebShop.Utilities.Log;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.*;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * TestNG listener to hook into test lifecycle.
 * On failure, captures screenshot and attaches it (and video if available) to Allure.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (!(testInstance instanceof BaseTest)) {
            return;
        }

        BaseTest baseTest = (BaseTest) testInstance;
        Page page = baseTest.getPage();

        try {
            if (ConfigManager.isScreenshotOnFailure() && page != null) {
                byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                        .setFullPage(true));
                Allure.addAttachment("Failure Screenshot",
                        new ByteArrayInputStream(screenshot));
            }

            // Attach video if recorded
            if ("on".equalsIgnoreCase(ConfigManager.getVideoMode())
                    && baseTest.getContext() != null) {
                baseTest.getContext().pages().forEach(p -> {
                    try {
                        Path videoPath = p.video() != null ? p.video().path() : null;
                        if (videoPath != null && Files.exists(videoPath)) {
                            Allure.addAttachment("Failure Video: " + videoPath.getFileName(),
                                    "video/webm",
                                    Files.newInputStream(videoPath),
                                    ".webm");
                        }
                    } catch (Exception e) {
                        Log.getLogger(getClass()).error("Failed to attach video", e);
                    }
                });
            }
        } catch (Exception e) {
            Log.getLogger(getClass()).error("Error capturing failure artifacts", e);
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
