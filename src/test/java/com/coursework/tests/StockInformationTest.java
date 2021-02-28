package com.coursework.tests;

import com.coursework.pages.HomePage;
import com.coursework.pages.SearchResultPage;
import com.coursework.pages.StatisticsPage;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.CsvReader;

import java.io.IOException;

@Slf4j
public class StockInformationTest extends BaseTest {

    @DataProvider(name = "stock-data", parallel = true)
    public static Object[][] dataProviderFromFile() throws IOException, CsvException {
        return CsvReader.getCSVDataWithHeader("src/test/resources/stockData0.csv");
    }

    @Test(dataProvider = "stock-data")
    public void testData(String companyName, String dividendAndYield, String priceOverBookMRQ) {

        HomePage homePage = new HomePage(drivers.get(Thread.currentThread().getId()));
        homePage.acceptCookiesPolicy();

        //soft asserts are used as stock information is quite volatile
        SearchResultPage searchResultPage = homePage.search(companyName);
        new SoftAssert().assertEquals(searchResultPage.getDividendAndYield(), dividendAndYield);

        StatisticsPage statisticsPage = searchResultPage.clickStatistics();
        new SoftAssert().assertEquals(statisticsPage.getPriceOverBookMRQ(), priceOverBookMRQ);

    }
}
