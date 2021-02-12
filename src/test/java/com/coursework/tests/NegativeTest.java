package com.coursework.tests;

import com.coursework.pages.HomePage;
import com.coursework.pages.SignInPage;
import com.coursework.pages.SignUpPage;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CsvReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
public class NegativeTest extends BasePageTest {

    @DataProvider(name = "negative-data")
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.getCSVDataWithHeader("src/test/resources/negativeData.csv");
    }

    @Test(dataProvider = "negative-data")
    public void signUp(String firstName, String lastName, String yahooId, String password, String phoneCountryCode,
                       String phonePostFix, String birthMonth, String birthDay, String birthYear, String gender) {
        List<String> expectedErrors = new ArrayList<>(Arrays.asList(
                "This email address is not available for sign up, try something else",
                "A Yahoo account already exists with this email address. Sign in.",
                "Your password is not strong enough. Please use a stronger password.",
                "Your password isn’t strong enough, try making it longer.",
                "That doesn’t look right, please re-enter your phone number.",
                "We don't support this number, provide another one.",
                "That doesn’t look right, please re-enter your birthday."));

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesPolicy();
        assertTrue(homePage.isInitialized());

        SignInPage signInPage = homePage.signIn();
        assertTrue(signInPage.isInitialized());

        SignUpPage signUpPage = signInPage.createAccount();
        assertTrue(signUpPage.isInitialized());

        signUpPage.enterName(firstName, lastName);

        signUpPage.enterYahooId(yahooId);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(signUpPage.getYahooIdError()));
        assertTrue(expectedErrors.contains(signUpPage.getYahooIdError().getText()));

        signUpPage.enterPassword(password);
        wait.until(ExpectedConditions.visibilityOf(signUpPage.getPasswordError()));
        assertTrue(expectedErrors.contains(signUpPage.getPasswordError().getText()));

        signUpPage.enterPhone(phoneCountryCode, phonePostFix);
        wait.until(ExpectedConditions.visibilityOf(signUpPage.getPhoneError()));
        assertTrue(expectedErrors.contains(signUpPage.getPhoneError().getText()));

        signUpPage.enterBirthDate(birthMonth, birthDay, birthYear);
        wait.until(ExpectedConditions.visibilityOf(signUpPage.getBirthDateError()));
        assertTrue(expectedErrors.contains(signUpPage.getBirthDateError().getText()));

        signUpPage.enterGender(gender);
    }
}
