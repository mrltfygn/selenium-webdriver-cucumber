package com.academy.ourWebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by dlammers on 2/27/2017.
 */
public class OurFirefoxDriver extends FirefoxDriver implements OurWebDriver {

    private static OurFirefoxDriver browser;

    private OurFirefoxDriver() {

    }

    static OurFirefoxDriver getBrowser() {
        System.setProperty("webdriver.gecko.driver", BrowserFactory.getDriverFile("gecko"));
        if (browser == null) {
            browser = new OurFirefoxDriver();
        } else if (browser.getSessionId() == null) {
            browser = new OurFirefoxDriver();
        }
        return browser;
    }

    public WebElement waitForElement(final String selector) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }

    public WebElement waitForElement(final By by) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForVisible(final String selector) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    public WebElement waitForVisible(final By by) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForClickable(final String selector) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
    }

    public WebElement waitForClickable(final By by) {
        final WebDriverWait wait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement scrollToElement(final By by) {
        final WebElement element = browser.findElement(by);
        scrollToElement(element);
        return element;
    }

    public void scrollToElement(final WebElement element) {
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForAjax() {
        final WebDriverWait webDriverWait = new WebDriverWait(browser, EXPLICIT_WAIT_TIMEOUT);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                final String scriptToExecute =
                        "return jQuery.active == 0;";
                final Boolean ajaxDone = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(scriptToExecute).toString());
                return ajaxDone ? true : null;
            }
        });
    }


}
