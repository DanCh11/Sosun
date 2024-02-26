package de.daycu.sosun.utils;

import de.daycu.sosun.exceptions.UnsupportedFileFormatException;
import de.daycu.sosun.models.PhoneNumber;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


public final class Fixtures {

    public static final PhoneNumber phoneNumber = new PhoneNumber(1L, "+4917676478693");

    public static final List<PhoneNumber> phoneNumbers = Arrays.asList(
        new PhoneNumber(1L, "+4917676478693"),
        new PhoneNumber(2L, "+491234567890")
    );

    public static MockMultipartFile csvWithPhoneNumbers() throws UnsupportedFileFormatException {
        try (StringWriter stringWriter = new StringWriter();
             CSVPrinter csvPrinter = new CSVPrinter(stringWriter, CSVFormat.DEFAULT
                     .builder()
                     .setHeader("ID", "PhoneNumber")
                     .setSkipHeaderRecord(true)
                     .build())) {

            for (PhoneNumber phoneNumber : phoneNumbers) {
                csvPrinter.printRecord(phoneNumber.getId(), phoneNumber.getPhoneNumber());
            }

            String csvContent = stringWriter.toString();

            return new MockMultipartFile(
                    "phoneNumbers.csv",
                    "phoneNumbers.csv",
                    "text/csv",
                    csvContent.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedFileFormatException("Only CSV files are supported.");
    }
}
