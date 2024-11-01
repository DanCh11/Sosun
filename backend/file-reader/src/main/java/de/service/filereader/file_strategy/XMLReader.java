package de.service.filereader.file_strategy;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import de.service.filereader.Contact;
import de.service.filereader.FileType;
import de.service.filereader.FileTypeConstants;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@FileType(FileTypeConstants.XML)
public class XMLReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    XmlMapper xmlMapper = new XmlMapper();

    return Collections.singletonList(xmlMapper.readValue(file, Contact.class));
  }

}
