package de.service.filereader.file_strategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.service.filereader.Contact;
import de.service.filereader.FileType;
import de.service.filereader.FileTypeConstants;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@FileType(FileTypeConstants.JSON)
public class JSONReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    return Collections.singletonList(objectMapper.readValue(file, Contact.class));
  }

}
