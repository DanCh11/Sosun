package de.service.filereader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JSONReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    return Collections.singletonList(objectMapper.readValue(file, Contact.class));
  }

}
