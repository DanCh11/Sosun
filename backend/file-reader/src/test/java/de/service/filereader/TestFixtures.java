package de.service.filereader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class TestFixtures {
  protected static final String CSV_PATH = "src/test/resources/mockPhoneNumbersList.csv";
  protected static final String JSON_PATH = "src/test/resources/mockPhoneNumbersList.json";
  protected static final String XML_PATH = "src/test/resources/mockPhoneNumbersList.xml";
  protected static final String YML_PATH = "src/test/resources/mockPhoneNumbersList.yml";
  protected static final String TXT_PATH = "src/test/resources/mockPhoneNumbersList.txt";

  protected static final File CSV_FILE = new File(CSV_PATH);
  protected static final File JSON_FILE = new File(JSON_PATH);
  protected static final File XML_FILE = new File(XML_PATH);
  protected static final File YML_FILE = new File(YML_PATH);
  protected static final File TXT_FILE = new File(TXT_PATH);

  protected static final Contact BOB_CONTACT = Contact.builder()
    .id(1L)
    .phoneNumber("+49123123122")
    .build();

  protected static final Contact BILLY_CONTACT = Contact.builder()
    .id(2L)
    .phoneNumber("+491237631321")
    .build();

  protected static final List<Contact> BOB_BILLY_CONTACTS = Arrays.asList(
    BOB_CONTACT,
    BILLY_CONTACT);
  
}
