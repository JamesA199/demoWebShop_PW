/*BrowserFactory
Owns Playwright lifecycle
Owns Browser launch
Owns Context creation
Owns HTTPSignore
Owns video recording
Owns environment driven config

BaseTest
Only:
asks BrowserFactory for a context
starts tracing
creates a page
navigates*/


package com.demoWebShop.FactoryPg;

import com.demoWebShop.Utilities.ConfigManager;
import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class BrowserFactory {

    private Playwright playwright;
    private Browser browser;

    public BrowserContext createBrowserContext() {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(ConfigManager.isHeadless());

        String browserName = ConfigManager.getBrowser().toLowerCase();
        switch (browserName) {
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "webkit":
                browser = playwright.webkit().launch(launchOptions);
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(launchOptions);
                break;
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setIgnoreHTTPSErrors(true); // <--- SSL FIX

        if ("on".equalsIgnoreCase(ConfigManager.getVideoMode())) {
            contextOptions.setRecordVideoDir(Paths.get("target/videos"));
            contextOptions.setRecordVideoSize(1280, 720);
        }

        return browser.newContext(contextOptions);
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void close() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
