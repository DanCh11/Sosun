package de.service.filereader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CSVReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    CsvMapper csvMapper = new CsvMapper();
    MappingIterator<Contact> contacts = csvMapper.readerWithTypedSchemaFor(Contact.class).readValues(file);

    return contacts.readAll();
  }
}
