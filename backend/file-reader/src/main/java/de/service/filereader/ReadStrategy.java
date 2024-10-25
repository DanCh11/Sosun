package de.service.filereader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ReadStrategy {
  List<Contact> read(File file) throws IOException;
}
