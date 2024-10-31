package de.service.filereader;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
