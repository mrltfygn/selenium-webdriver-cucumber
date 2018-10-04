package com.capgemini.ourWebdriver;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

public class TearDown {

    private final WebDriver browser;

    public TearDown() {
        browser = BrowserFactory.getWebDriver();
    }

    @After
    public void afterScenario() throws InterruptedException {
        Thread.sleep(5000);
        browser.quit();
    }
}
