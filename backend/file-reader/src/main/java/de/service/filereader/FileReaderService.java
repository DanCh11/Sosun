package de.service.filereader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import de.service.filereader.file_strategy.ReadStrategy;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileReaderService {

  private ReadStrategy strategy;

  public List<Contact> processFile(File file) throws IOException {
    String fileType = strategy.detectFileType(file);
    isSupported(strategy, fileType);

    return strategy.read(file);
  }

  /**
   * Addresses the verification of file type support.
   * @param strategy supported file strategies
   * @param fileType given file type
   * 
   * @return whether file is supported or not
   */
  private boolean isSupported(ReadStrategy strategy, String fileType) {
    FileType annotation = strategy.getClass().getAnnotation(FileType.class);

    return annotation != null && annotation.value().equalsIgnoreCase(fileType);
  }

}
