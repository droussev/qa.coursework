package com.coursework.tests;

import com.coursework.pages.HomePage;
import com.coursework.pages.SignInPage;
import com.coursework.pages.SignUpPage;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CsvReader;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class NegativeTest extends BaseTest {

    @DataProvider(name = "negative-data", parallel = true)
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.getCSVDataWithHeader("src/test/resources/negativeData.csv");
    }

    @Test(dataProvider = "negative-data", threadPoolSize = 4)
    public void signUp(String firstName, String lastName, String yahooId, String password, String phoneCountryCode,
                       String phonePostFix, String birthMonth, String birthDay, String birthYear, String gender,
                       String yidError, String passwordError, String phoneError, String birthDayError) {

        HomePage homePage = new HomePage(drivers.get(Thread.currentThread().getId()));
        homePage.acceptCookiesPolicy();

        SignInPage signInPage = homePage.signIn();
        SignUpPage signUpPage = signInPage.createAccount();

        signUpPage.enterName(firstName, lastName);

        signUpPage.enterYahooId(yahooId);
        assertTrue(signUpPage.hasYahooIdError(yidError));

        signUpPage.enterPassword(password);
        assertTrue(signUpPage.hasPasswordError(passwordError));

        signUpPage.enterPhone(phoneCountryCode, phonePostFix);
        assertTrue(signUpPage.hasPhoneError(phoneError));

        signUpPage.enterBirthDate(birthMonth, birthDay, birthYear);
        assertTrue(signUpPage.hasBirthDateError(birthDayError));

        signUpPage.enterGender(gender);
        //signUpPage.Destroy();
    }
}
