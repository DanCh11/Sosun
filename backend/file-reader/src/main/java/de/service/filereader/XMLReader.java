package de.service.filereader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class XMLReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    XmlMapper xmlMapper = new XmlMapper();

    return Collections.singletonList(xmlMapper.readValue(file, Contact.class));
  }

}
