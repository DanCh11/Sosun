package de.service.fileuploader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class FileSystemStorageServiceTest {

    private final static String CSV_FILE_PATH = "src/test/resources/mockPhoneNumbersList.csv";
    private final static File CSV_FILE = new File(CSV_FILE_PATH);

    @MockBean
    private FileSystemStorageService fileSystemStorageService;

    @Test
    void testLoad() throws IOException {
        when(this.fileSystemStorageService.loadFile(CSV_FILE_PATH)).thenReturn(CSV_FILE);

        File file = this.fileSystemStorageService.loadFile(CSV_FILE_PATH);

        assertNotNull(file);
        assertEquals(file.getName(), "mockPhoneNumbersList.csv");
        assertTrue(file.exists(), "CSV File exists");
    }

}
