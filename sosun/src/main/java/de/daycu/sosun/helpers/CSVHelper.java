package de.daycu.sosun.helpers;

import de.daycu.sosun.models.PhoneNumber;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String TYPE = "text/csv";
    private static final String[] HEADERS = {"ID", "PhoneNumber"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static Iterable<PhoneNumber> csvToPhoneNumbers(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(
                     fileReader,
                     CSVFormat.DEFAULT.builder().setHeader(HEADERS).build())) {

            List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

            for (CSVRecord csvRecord : csvParser.getRecords()) {
                PhoneNumber phoneNumber = new PhoneNumber(
                        Long.parseLong(csvRecord.get("ID")),
                        csvRecord.get("PhoneNumber"));

                phoneNumbers.add(phoneNumber);
            }

            return phoneNumbers;

        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

}
