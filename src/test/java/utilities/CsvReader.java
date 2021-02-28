package utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {

    public static <split_record> Object[][] getCSVDataWithHeader(String fileName) throws IOException, CsvException {

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            List<String[]> csvData = csvReader.readAll();
            return csvData.toArray(new Object[0][]);
        }
    }
}