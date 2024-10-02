package de.service.fileuploader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class FileSystemStorageServiceTest {

    private final static String CSV_FILE_PATH = "src/test/resources/mockPhoneNumbersList.csv";

    @MockBean
    private FileSystemStorageService fileSystemStorageService;

    @Test
    void testLoad() {
        when(this.fileSystemStorageService.loadFile(CSV_FILE_PATH)).thenReturn(Path.of(CSV_FILE_PATH));

        Path path = this.fileSystemStorageService.loadFile(CSV_FILE_PATH);

        assertNotNull(path);
        assertEquals(path.getFileName().toString(), "mockPhoneNumbersList.csv");
        assertTrue(Files.exists(path), "CSV File exists");
    }

}
