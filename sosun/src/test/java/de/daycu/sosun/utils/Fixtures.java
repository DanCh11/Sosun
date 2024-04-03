package de.daycu.sosun.utils;

import de.daycu.sosun.exceptions.UnsupportedFileFormatException;
import de.daycu.sosun.models.ContactPhoneNumber;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


public final class Fixtures {

    public static final ContactPhoneNumber phoneNumber = new ContactPhoneNumber(1L, "+4917676478693");

    public static final List<ContactPhoneNumber> phoneNumbers = Arrays.asList(
        new ContactPhoneNumber(1L, "+4917676478693"),
        new ContactPhoneNumber(2L, "+491234567890")
    );

    public static MockMultipartFile csvWithPhoneNumbers() throws UnsupportedFileFormatException {
        try (StringWriter stringWriter = new StringWriter();
             CSVPrinter csvPrinter = new CSVPrinter(stringWriter, CSVFormat.DEFAULT
                     .builder()
                     .setHeader("ID", "PhoneNumber")
                     .setSkipHeaderRecord(true)
                     .build())) {

            for (ContactPhoneNumber phoneNumber : phoneNumbers) {
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
