package de.service.fileuploader;

import de.service.fileuploader.exceptions.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService {

  private final Path rootLocation;

  public FileSystemStorageService(StorageProperties storageProperties) {
      if (!StringUtils.hasText(storageProperties.getLocation())) {
          throw new IllegalArgumentException("File Upload Location Cannot Be Empty.");
      }
      this.rootLocation = Paths.get(storageProperties.getLocation());
  }

  public void init() {
      try {
          Files.createDirectories(rootLocation);
      } catch (IOException e) {
          throw new StorageException("Could not initialize storage", e);
      }
  }

  public File loadFile(String filename) {
      return rootLocation.resolve(filename).toFile();
  }
}
