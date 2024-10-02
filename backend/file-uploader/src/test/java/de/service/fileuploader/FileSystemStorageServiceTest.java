package de.service.fileuploader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
class FileSystemStorageServiceTest {

    private final static String CSV_FILE_PATH = "src/test/resources/mockPhoneNumbersList.csv";
    private final static String JSON_FILE_PATH = "src/test/resources/mockPhoneNumbersList.json";

    @MockBean
    private FileSystemStorageService fileSystemStorageService;

    @Test
    void testLoadAll() {
        when(this.fileSystemStorageService.loadAll()).thenReturn(Stream.of(
                Paths.get(CSV_FILE_PATH), Paths.get(JSON_FILE_PATH))
        );

        List<Path> paths = this.fileSystemStorageService.loadAll().toList();

        assertNotNull(paths);
        assertEquals(2, paths.size());
        assertTrue(Files.exists(paths.get(0)), "CSV File exists");
        assertTrue(Files.exists(paths.get(1)), "JSON File exists");
    }

    @Test
    void testLoad() {
        when(this.fileSystemStorageService.load(CSV_FILE_PATH)).thenReturn(Path.of(CSV_FILE_PATH));

        Path path = this.fileSystemStorageService.load(CSV_FILE_PATH);

        assertNotNull(path);
        assertTrue(Files.exists(path), "CSV File exists");
    }

    @Test
    void testDeleteAll() {
        when(this.fileSystemStorageService.loadAll()).thenReturn(Stream.of(
                Paths.get(CSV_FILE_PATH), Paths.get(JSON_FILE_PATH))
        );
        List<Path> paths = this.fileSystemStorageService.loadAll().toList();

        assertNotNull(paths);
        assertEquals(2, paths.size());

        doNothing().when(this.fileSystemStorageService).deleteAll();

    }


}
