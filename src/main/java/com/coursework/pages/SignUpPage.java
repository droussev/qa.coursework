package com.coursework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//div[@id='login-body']")
    private WebElement pageBody;
    @FindBy(xpath = "//input[@id='usernamereg-firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@id='usernamereg-lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@id='usernamereg-yid']")
    private WebElement yahooId;
    @FindBy(xpath = "//input[@id='usernamereg-password']")
    private WebElement password;
    @FindBy(xpath = "//select[@name='shortCountryCode']")
    private WebElement countryCode;
    @FindBy(xpath = "//input[@id='usernamereg-phone']")
    private WebElement phone;
    @FindBy(xpath = "//select[@id='usernamereg-month']")
    private WebElement birthMonth;
    @FindBy(xpath = "//input[@id='usernamereg-day']")
    private WebElement birthDay;
    @FindBy(xpath = "//input[@id='usernamereg-year']")
    private WebElement birthYear;
    @FindBy(xpath = "//input[@name='freeformGender']")
    private WebElement gender;
    @FindBy(xpath = "//div[@id='reg-error-yid']")
    private WebElement yahooIdError;
    @FindBy(xpath = "//div[@id='reg-error-password']")
    private WebElement passwordError;
    @FindBy(xpath = "//div[@id='reg-error-phone']")
    private WebElement phoneError;
    @FindBy(xpath = "//div[@id='reg-error-birthDate']")
    private WebElement birthDateError;
    @FindBy(xpath = "//button[@id='reg-submit-button']")
    private WebElement submit;

    private final boolean BLUR_POST_ENTRY = true; //lose focus to get processing of input done so errors would display

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return firstName.isDisplayed();
    }

    public void enterName(String firstName, String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);

        this.lastName.clear();
        this.lastName.sendKeys(lastName);

        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public void enterYahooId(String yid) {
        yahooId.clear();
        yahooId.sendKeys(yid);
        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public void enterPhone(String countryCode, String phoneNumber) {
        Select dropDown = new Select(this.countryCode);
        dropDown.selectByValue(countryCode);

        phone.clear();
        phone.sendKeys(phoneNumber);
        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public void enterBirthDate(String month, String day, String year) {
        Select dropDown = new Select(this.birthMonth);
        dropDown.selectByValue(month);

        birthDay.clear();
        birthDay.sendKeys(day);

        birthYear.clear();
        birthYear.sendKeys(year);

        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public void enterGender(String gender) {
        this.gender.clear();
        this.gender.sendKeys(gender);
        if (BLUR_POST_ENTRY) pageBody.click();
    }

    public WebElement getYahooIdError() {
        return yahooIdError;
    }

    public WebElement getPasswordError() {
        return passwordError;
    }

    public WebElement getPhoneError() {
        return phoneError;
    }


    public WebElement getBirthDateError() {
        return birthDateError;
    }

    public void submit() {
        this.submit.click();
    }
}
