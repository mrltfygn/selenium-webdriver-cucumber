package com.academy.ourWebdriver;

import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.util.Properties;

import static com.academy.utils.FileHelper.getRootPath;

public class BrowserFactory {

    private static OurWebDriver browser;

    public static OurWebDriver getWebDriver() {
        final String browserType = getBrowserType();
        return getBrowserOfType(browserType);
    }

    static String getDriverFile(String browserName) {
        FileFilter fileFilter = new WildcardFileFilter("*"+browserName+"*");
        File[] dir = new File(getRootPath() + File.separator + "drivers" + File.separator).listFiles(fileFilter);
        for(int i = 0; i < dir.length; i++){
            if(dir[i].getName().contains("version")) {
                continue;
            }
            return dir[i].getAbsolutePath();
        }
        throw new IllegalStateException("No driver found for '" + browserName + "' in the folder drivers.\nStopping execution.");
    }

    private static OurWebDriver getBrowserOfType(final String browserType) {
        if (browserType == null) {
            browser = OurFirefoxDriver.getBrowser();
        } else if (browserType.equals("chrome")) {
            browser = OurChromeDriver.getBrowser();
        } else if (browserType.equals("ie")) {
            browser = OurIEDriver.getBrowser();
        } else {
            browser = OurFirefoxDriver.getBrowser();
        }
        return browser;
    }

    private static String getBrowserType() {
        final Properties prop = new Properties();
        final InputStream input;
        String browserType = null;

        try {
            input = new FileInputStream(getRootPath() + File.separator + "browser.properties");
            prop.load(input);
            browserType = prop.getProperty("browser.type");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return browserType;
    }
}
