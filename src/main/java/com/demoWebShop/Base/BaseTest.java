package com.demoWebShop.Base;

import com.demoWebShop.Utilities.ConfigManager;
import com.demoWebShop.FactoryPg.BrowserFactory;
import com.demoWebShop.Utilities.AllureUtils;
import com.demoWebShop.Utilities.LogUtils;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseTest {
	/*protected means:
	visible to subclasses
	visible to classes in the same package
	NOT visible to classes in other packages*/
	
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        LogUtils.info("Starting test setup");

        // --- Use BrowserFactory as the single source of truth ---
        BrowserFactory factory = new BrowserFactory();
        context = factory.createBrowserContext();
        browser = factory.getBrowser();
        playwright = factory.getPlaywright();

        // --- Start tracing ---
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        // --- Create page ---
        page = context.newPage();

        // --- Navigate ---
        String baseUrl = ConfigManager.get("baseUrl");
        LogUtils.info("Navigating to base URL: " + baseUrl);
        page.navigate(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        LogUtils.info("Starting test teardown");

        String testName = result.getMethod().getMethodName();

        try {
            // Screenshot on failure
            if (!result.isSuccess()) {
                LogUtils.warn("Test failed, capturing screenshot: " + testName);
                AllureUtils.attachScreenshot(page);
            }

            // Stop trace and attach path
            Path tracePath = Paths.get("traces", testName + "-trace.zip");
            context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
            AllureUtils.attachText("Trace file", tracePath.toString());

            // Attach video path
            if (!context.pages().isEmpty()) {
                Path videoPath = context.pages().get(0).video().path();
                AllureUtils.attachText("Video file", videoPath.toString());
            }

        } catch (Exception e) {
            LogUtils.error("Error during teardown for test: " + testName, e);
        } finally {
            // These objects were created by BrowserFactory, but BaseTest owns the lifecycle
            if (context != null) context.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        }
    }


    public Page getPage() {
        return page;
    }
    public BrowserContext getContext() {
        return context;
    }
    
}
