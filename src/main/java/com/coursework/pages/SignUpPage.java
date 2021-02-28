package com.coursework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {

    @FindBy(id = "login-body")
    private WebElement pageBody;

    @FindBy(id = "usernamereg-firstName")
    private WebElement firstName;

    @FindBy(id = "usernamereg-lastName")
    private WebElement lastName;

    @FindBy(id = "usernamereg-yid")
    private WebElement yahooId;

    @FindBy(id = "usernamereg-password")
    private WebElement password;

    @FindBy(name = "shortCountryCode")
    private WebElement countryCode;

    @FindBy(id = "usernamereg-phone")
    private WebElement phone;

    @FindBy(id = "usernamereg-month")
    private WebElement birthMonth;

    @FindBy(id = "usernamereg-day")
    private WebElement birthDay;

    @FindBy(id = "usernamereg-year")
    private WebElement birthYear;

    @FindBy(id = "usernamereg-freeformGender")
    private WebElement gender;

    @FindBy(id = "reg-error-yid")
    private WebElement yahooIdError;

    @FindBy(id = "reg-error-password")
    private WebElement passwordError;

    @FindBy(id = "reg-error-phone")
    private WebElement phoneError;

    @FindBy(id = "reg-error-birthDate")
    private WebElement birthDateError;

    @FindBy(id = "reg-submit-button")
    private WebElement submit;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String firstName, String lastName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
        pageBody.click();

        this.lastName.clear();
        this.lastName.sendKeys(lastName);
        pageBody.click();
    }

    public void enterYahooId(String yid) {
        yahooId.clear();
        yahooId.sendKeys(yid);
        pageBody.click();
    }

    public void enterPassword(String pass) {
        password.clear();
        password.sendKeys(pass);
        pageBody.click();
    }

    public void enterPhone(String countryCode, String phoneNumber) {
        Select dropDown = new Select(this.countryCode);
        dropDown.selectByValue(countryCode);

        phone.clear();
        phone.sendKeys(phoneNumber);
        pageBody.click();
    }

    public void enterBirthDate(String month, String day, String year) {
        Select dropDown = new Select(birthMonth);
        dropDown.selectByValue(month);

        birthDay.clear();
        birthDay.sendKeys(day);

        birthYear.clear();
        birthYear.sendKeys(year);
        pageBody.click();
    }

    public void enterGender(String gender) {
        this.gender.clear();
        this.gender.sendKeys(gender);
    }

    private boolean hasError(WebElement errorElement, String errors) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(errorElement));
        String errorText = errorElement.getText();

        return errors.replace("{comma}",",").contains(errorText);
    }

    public boolean hasYahooIdError(String errors) {
        return hasError(yahooIdError, errors);
    }

    public boolean hasPasswordError(String errors) {
        return hasError(passwordError, errors);
    }

    public boolean hasPhoneError(String errors) {
        return hasError(phoneError, errors);
    }


    public boolean hasBirthDateError(String errors) {
        return hasError(birthDateError, errors);
    }

    public void submit() {
        submit.click();
    }
}
