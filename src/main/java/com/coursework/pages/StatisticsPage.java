package com.coursework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatisticsPage extends BasePage {

    @FindBy(xpath = "//table//tr//td//span[text()='Price/Book']/ancestor::td/following-sibling::td")
    private WebElement priceOverBookMostRecentQuarter;

    public StatisticsPage(WebDriver driver) {
        super(driver);
    }

    public String getPriceOverBookMRQ() {
        return priceOverBookMostRecentQuarter.getText();
    }

    public boolean isInitialized() {
        return priceOverBookMostRecentQuarter.isDisplayed();
    }
}
