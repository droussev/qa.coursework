package com.coursework.tests;

import com.coursework.pages.HomePage;
import com.coursework.pages.SearchResultPage;
import com.coursework.pages.StatisticsPage;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CsvReader;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

@Slf4j
public class StockInformationTest extends BasePageTest {

    @DataProvider(name = "stock-data")
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.getCSVDataWithHeader("src/test/resources/stockData0.csv");
    }

    @Test(dataProvider = "stock-data")
    public void testData(String companyName, String dividendAndYield, String priceOverBookMRQ) {

        HomePage homePage = new HomePage(driver);
        homePage.acceptCookiesPolicy();
        assertTrue(homePage.isInitialized());

        SearchResultPage searchResultPage = homePage.search(companyName);
        assertTrue(searchResultPage.isInitialized());
        assertEquals(searchResultPage.getDividendAndYield(), dividendAndYield);

        StatisticsPage statisticsPage = searchResultPage.clickStatistics();
        assertTrue(statisticsPage.isInitialized());
        assertEquals(statisticsPage.getPriceOverBookMRQ(), priceOverBookMRQ);
    }
}
