package de.service.fileuploader;

import java.nio.file.Path;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/storage")
@AllArgsConstructor
public class FileSystemStorageController {

  private FileSystemStorageService fileSystemStorageService;

  @GetMapping("/file/{filename}")
  public Path loadFile(@PathVariable("filename") String filename) {
    return fileSystemStorageService.loadFile(filename);
  }

}
