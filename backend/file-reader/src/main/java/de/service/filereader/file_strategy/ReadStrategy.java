package de.service.filereader.file_strategy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.service.filereader.Contact;

public interface ReadStrategy {
  List<Contact> read(File file) throws IOException;

  default String detectFileType(File file) {
    final int EMPTY_EXTENSION = -1;
    String name = file.getName();
    int lastIndexOf = name.lastIndexOf(".");

    if (lastIndexOf == EMPTY_EXTENSION) {
      return "";
    }
    return name.substring(lastIndexOf);
  }
}
