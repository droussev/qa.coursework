package com.coursework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {
    @FindBy(xpath = "//span[text()='Forward Dividend & Yield']/ancestor::tr/td[contains(text(),'(')]")
    private WebElement forwardDividendAndYield;

    @FindBy(xpath = "//span[text()='Statistics']")
    private WebElement statisticsLink;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getDividendAndYield() {
        return forwardDividendAndYield.getText();
    }

    public StatisticsPage clickStatistics() {
        statisticsLink.click();
        return new StatisticsPage(driver);
    }
}
