package com.coursework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class = 'menu-section']//a[@id='header-signin-link']")
    private WebElement loginLink;

    @FindBy(xpath = "//input[@id='yfin-usr-qry']")
    private WebElement searchBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return loginLink.isDisplayed();
    }

    public void acceptCookiesPolicy() {
        try {
            driver.findElement(By.name("agree")).click();
        } catch (NoSuchElementException e) {
            //do nothing
        }
        //driver.switchTo().defaultContent();
    }

    public SignInPage signIn() {
        this.loginLink.click();
        return new SignInPage(driver);
    }


    public SearchResultPage search(String query) {
        String xpath = "//ul//li[starts-with(@class, 'modules_link') and @data-pindex=1 and contains(@title,'QUERY')]";
        this.searchBar.clear();
        this.searchBar.sendKeys(query);
        WebElement dropDownSuggestedItem = driver.findElement(By.xpath(xpath.replace("QUERY", query)));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(dropDownSuggestedItem));
        dropDownSuggestedItem.click();
        return new SearchResultPage(driver);
    }

}
