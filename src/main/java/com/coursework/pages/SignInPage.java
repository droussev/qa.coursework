package com.coursework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    @FindBy(xpath = "//a[@id='createacc']")
    private WebElement createAccount;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return createAccount.isDisplayed();
    }

    public SignUpPage createAccount() {
        createAccount.click();
        return new SignUpPage(driver);
    }


}
