package com.academy.hooks;

import com.academy.driver.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class WebHook {

    private final Browser browser = Browser.getInstance();

    @Before
    public void beforeScenario() {
        browser.startBrowser();
    }

    @After
    public void afterScenario(Scenario scenario) throws InterruptedException {
        Thread.sleep(5000);
        if (scenario.isFailed()) {
            byte[] screenshot = browser.makeScreenshot();
            scenario.attach(screenshot, "image/png", "name");
        }
        browser.quit();
    }

}
