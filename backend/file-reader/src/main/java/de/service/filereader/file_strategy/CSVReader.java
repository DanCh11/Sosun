package de.service.filereader.file_strategy;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import de.service.filereader.Contact;
import de.service.filereader.FileType;
import de.service.filereader.FileTypeConstants;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@FileType(FileTypeConstants.CSV)
public class CSVReader implements ReadStrategy {

  @Override
  public List<Contact> read(File file) throws IOException {
    CsvMapper csvMapper = new CsvMapper();
    MappingIterator<Contact> contacts = csvMapper.readerWithTypedSchemaFor(Contact.class).readValues(file);

    return contacts.readAll();
  }
}
