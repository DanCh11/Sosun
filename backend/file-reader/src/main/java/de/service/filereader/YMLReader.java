package de.service.filereader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class YMLReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    return Collections.singletonList(mapper.readValue(file, Contact.class));
  }

}
