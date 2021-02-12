package com.coursework.tests;

import com.coursework.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BasePageTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = WebDriverProvider.getWebDriver("src/main/resources/webdriver.properties");
    }

    @AfterClass
    public static void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
