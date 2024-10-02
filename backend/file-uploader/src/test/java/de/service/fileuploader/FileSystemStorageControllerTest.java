package de.service.fileuploader;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(FileSystemStorageController.class)
public class FileSystemStorageControllerTest {

  private final static String CSV_FILE_PATH = "src/test/resources/mockPhoneNumbersList.csv";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FileSystemStorageService fileSystemStorageService;

  @Test
  void testLoadFile() throws Exception {
    when(fileSystemStorageService.loadFile(anyString())).thenReturn(Path.of(CSV_FILE_PATH));

    ResultActions response = mockMvc
        .perform(get("/api/v1/storage/file/{CSV_FILE_PATH}", "mockPhoneNumbersList.csv")
            .contentType(MediaType.APPLICATION_JSON));

    response.andDo(print())
        .andExpect(status().is2xxSuccessful());
  }
}
