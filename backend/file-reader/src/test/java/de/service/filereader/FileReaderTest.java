package de.service.filereader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith({ SpringExtension.class, MockitoExtension.class })
class FileReaderTest {
  private static final String CSV_PATH = "src/test/resources/mockPhoneNumbersList.csv";
  private static final String JSON_PATH = "src/test/resources/mockPhoneNumbersList.json";
  private static final String XML_PATH = "src/test/resources/mockPhoneNumbersList.xml";
  private static final String YML_PATH = "src/test/resources/mockPhoneNumbersList.yml";

  public static final Contact bobsContact = Contact.builder()
      .id(1L)
      .phoneNumber("+49123123122")
      .build();

  public static final Contact billysContact = Contact.builder()
      .id(2L)
      .phoneNumber("+491237631321")
      .build();

  public static final List<Contact> bobsAndBillysContacts = Arrays.asList(
      bobsContact,
      billysContact);

  @MockBean
  private CSVReader csvReader;

  @MockBean
  private JSONReader jsonReader;

  @MockBean
  private XMLReader xmlReader;

  @MockBean
  private YMLReader ymlReader;

  @Test
  void testCSVContactExtraction() throws IOException {
    File csvFile = new File(CSV_PATH);
    when(csvReader.read(csvFile)).thenReturn(bobsAndBillysContacts);

    List<Contact> extractedContacts = this.csvReader.read(csvFile);

    assertNotNull(extractedContacts);
    assertEquals(extractedContacts, bobsAndBillysContacts);
  }

  @Test
  void testJSONContactExtraction() throws IOException {
    File jsonFile = new File(JSON_PATH);
    when(jsonReader.read(jsonFile)).thenReturn(bobsAndBillysContacts);

    List<Contact> extractedContacts = this.jsonReader.read(jsonFile);

    assertNotNull(extractedContacts);
    assertEquals(extractedContacts, bobsAndBillysContacts);
  }

  @Test
  void testXMLContactExtraction() throws IOException {
    File xmlFile = new File(XML_PATH);
    when(xmlReader.read(xmlFile)).thenReturn(bobsAndBillysContacts);

    List<Contact> extractedContacts = this.xmlReader.read(xmlFile);

    assertNotNull(extractedContacts);
    assertEquals(extractedContacts, bobsAndBillysContacts);
  }

  @Test
  void testYMLContactExtraction() throws IOException {
    File ymlFile = new File(YML_PATH);
    when(ymlReader.read(ymlFile)).thenReturn(bobsAndBillysContacts);

    List<Contact> extractedContacts = this.ymlReader.read(ymlFile);

    assertNotNull(extractedContacts);
    assertEquals(extractedContacts, bobsAndBillysContacts);
  }

}
