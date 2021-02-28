package com.coursework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverProvider {
    private static String driverPropertiesFile;
    private static String browser;
    private static String url;
    private static int implicitWait;
    private static String[] browserOptions;

    public static void getDriverProperties() {
        try (InputStream input = new FileInputStream(driverPropertiesFile)) {
            Properties prop = new Properties();
            prop.load(input);
            browser = prop.getProperty("browser");
            url = prop.getProperty("url");
            String options = prop.getProperty("browserOptions");
            if (options != null) browserOptions = options.split(" ");
            implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static WebDriver getWebDriver(String... options) {

        if (options.length == 1) { //if only one string is passed use it as file name
            driverPropertiesFile = options[0];
            getDriverProperties();
        } else if (options.length == 4) {
            browser = options[0];
            url = options[1];
            browserOptions = options[2].split(" ");
            implicitWait = Integer.parseInt(options[3]);
        } else throw new UnsupportedOperationException();
        WebDriver driver = null;
        switch (browser) {
            case "Firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(browserOptions);
                WebDriverManager.getInstance(FirefoxDriver.class).setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(browserOptions);
                WebDriverManager.getInstance(ChromeDriver.class).setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }
}
