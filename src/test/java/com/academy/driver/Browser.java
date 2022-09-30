package com.academy.driver;

import com.academy.helpers.FileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * This class is responsible for all interactions with a web browser.
 */
public class Browser {

    private static Browser browser;
    private final FileReader fileReader = FileReader.getInstance();

    private WebDriver driver;
    private WebDriverWait wait;

    private static final int EXPLICIT_WAIT_TIME = 10;

    private static final String BROWSER_TYPE_PROPERTY_NAME = "browser.type";
    private static final String GET_PAGE_READYSTATE = "return document.readyState";
    private static final String READYSTATE_COMPLETE = "complete";

    private Browser() {
    }

    /**
     * Creates a new browser instance if one does not already exist.
     * If one already exists, it returns that instance.
     *
     * @return browser
     */
    public static Browser getInstance() {
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    /**
     * Instantiates the web driver. It retrieves the wanted browser type from the system properties at runtime.
     * Makes use of {@link io.github.bonigarcia} to download the latest driver binaries.
     */
    public void startBrowser() {
        BrowserType browserType = BrowserType.valueOf(fileReader.getProperty(BROWSER_TYPE_PROPERTY_NAME).toUpperCase());

        if (browserType.equals(BrowserType.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserType.equals(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserType.equals(BrowserType.SAFARI)) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        } else {
            throw new UnsupportedOperationException("BrowserType: " + browserType.name() + " not supported.");
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
        driver.manage().window().maximize();
    }

    /**
     * Loads a new web page in the current browser window.
     */
    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Finds all web elements that match the CSS selector.
     * Waits for the page to be fully loaded before searching the web elements.
     *
     * @param by        CSS selector or XPath
     * @return          List of all matching elements
     */
    public List<WebElement> findElements(By by) {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript(GET_PAGE_READYSTATE).equals(READYSTATE_COMPLETE));
        return driver.findElements(by);
    }

    /**
     * Returns the first web element that matches the CSS selector.
     * Waits for the page to be fully loaded before searching the web element.
     *
     * @param by        CSS selector or XPath
     * @return          WebElement
     */
    public WebElement findElement(By by) {
        // Hard wait to slow down the tests so trainees can see what's going on.
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }

        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript(GET_PAGE_READYSTATE).equals(READYSTATE_COMPLETE));
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public WebDriver.Options manage() {
        return driver.manage();
    }

    public void waitForElementToBeClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElementToBeVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
