package com.coursework.tests;

import com.coursework.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public abstract class BaseTest {
    //keep track of which WebDriver object is being used by which thread so we can dispose of them safely when done
    protected HashMap<Long, WebDriver> drivers = new HashMap<>();

    @BeforeMethod
    public void setUp() {
        WebDriver driver = WebDriverProvider.getWebDriver("src/main/resources/webdriver.properties");
        drivers.put(Thread.currentThread().getId(), driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = drivers.get(Thread.currentThread().getId());
        driver.manage().deleteAllCookies();
        drivers.remove(Thread.currentThread().getId());
        driver.quit();
    }
}
