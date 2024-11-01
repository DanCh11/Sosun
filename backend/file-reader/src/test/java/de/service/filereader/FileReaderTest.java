package de.service.filereader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.service.filereader.file_strategy.CSVReader;
import de.service.filereader.file_strategy.JSONReader;
import de.service.filereader.file_strategy.XMLReader;
import de.service.filereader.file_strategy.YMLReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static de.service.filereader.TestFixtures.*;

@ExtendWith({ SpringExtension.class, MockitoExtension.class })
class FileReaderTest {

  @Mock
  private CSVReader csvReader;

  @Mock
  private JSONReader jsonReader;

  @Mock
  private XMLReader xmlReader;

  @Mock
  private YMLReader ymlReader;

  @Mock
  private FileReaderService fileReaderService;

  @Test
  void testCSVContactExtraction() throws IOException {
    when(csvReader.read(CSV_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(csvReader.detectFileType(CSV_FILE)).thenReturn(FileTypeConstants.CSV);

    List<Contact> extractedContacts = this.csvReader.read(CSV_FILE);
    String fileExtensionOfCSV = this.csvReader.detectFileType(CSV_FILE);

    assertEquals(extractedContacts, BOB_BILLY_CONTACTS);
    assertEquals(fileExtensionOfCSV, FileTypeConstants.CSV);
  }

  @Test
  void testJSONContactExtraction() throws IOException {
    when(jsonReader.read(JSON_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(jsonReader.detectFileType(JSON_FILE)).thenReturn(FileTypeConstants.JSON);

    List<Contact> extractedContacts = this.jsonReader.read(JSON_FILE);
    String fileExtensionOfJSON = this.jsonReader.detectFileType(JSON_FILE);

    assertEquals(extractedContacts, BOB_BILLY_CONTACTS);
    assertEquals(fileExtensionOfJSON, FileTypeConstants.JSON);
  }

  @Test
  void testXMLContactExtraction() throws IOException {
    when(xmlReader.read(XML_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(xmlReader.detectFileType(XML_FILE)).thenReturn(FileTypeConstants.XML);

    List<Contact> extractedContacts = this.xmlReader.read(XML_FILE);
    String fileExtensionOfXML = this.xmlReader.detectFileType(XML_FILE);

    assertEquals(extractedContacts, BOB_BILLY_CONTACTS);
    assertEquals(fileExtensionOfXML, FileTypeConstants.XML);
  }

  @Test
  void testYMLContactExtraction() throws IOException {
    when(ymlReader.read(YML_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(ymlReader.detectFileType(YML_FILE)).thenReturn(FileTypeConstants.YML);

    List<Contact> extractedContacts = this.ymlReader.read(YML_FILE);
    String fileExtensionOfYML = this.ymlReader.detectFileType(YML_FILE);

    assertEquals(extractedContacts, BOB_BILLY_CONTACTS);
    assertEquals(fileExtensionOfYML, FileTypeConstants.YML);
  }

  @Test
  void testFileReaderService() throws IOException {
    when(fileReaderService.processFile(CSV_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(fileReaderService.processFile(JSON_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(fileReaderService.processFile(XML_FILE)).thenReturn(BOB_BILLY_CONTACTS);
    when(fileReaderService.processFile(YML_FILE)).thenReturn(BOB_BILLY_CONTACTS);

    List<Contact> extractedContactsFromCSV = this.fileReaderService.processFile(CSV_FILE);
    List<Contact> extractedContactsFromJSON = this.fileReaderService.processFile(JSON_FILE);
    List<Contact> extractedContactsFromXML = this.fileReaderService.processFile(XML_FILE);
    List<Contact> extractedContactsFromYML = this.fileReaderService.processFile(YML_FILE);

    assertEquals(extractedContactsFromCSV, BOB_BILLY_CONTACTS);
    assertEquals(extractedContactsFromJSON, BOB_BILLY_CONTACTS);
    assertEquals(extractedContactsFromXML, BOB_BILLY_CONTACTS);
    assertEquals(extractedContactsFromYML, BOB_BILLY_CONTACTS);

  }



}
