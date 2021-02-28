package com.coursework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    //a[contains(text(),'Sign in')]
    @FindBy(id = "header-signin-link")
    private WebElement loginLink;

    //form/input[@type='text']
    @FindBy(id = "yfin-usr-qry")
    private WebElement searchBar;

    //button[@name = 'agree']
    @FindBy(name = "agree")
    private WebElement cookieConsentButton;

    @FindBy(xpath = "//li[@data-pindex=1 and @data-type='quotes']") //should give us the top element of the search bar
    private WebElement dropDownSuggestedItem;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookiesPolicy() {
        List<WebElement> consentPage = driver.findElements(By.id("consent-page"));
        if (consentPage.size() != 0) {
            cookieConsentButton.click();
        }
    }

    public SignInPage signIn() {
        loginLink.click();
        return new SignInPage(driver);
    }

    public SearchResultPage search(String query) {
        searchBar.clear();
        searchBar.sendKeys(query);
        dropDownSuggestedItem.click();
        return new SearchResultPage(driver);
    }
}
