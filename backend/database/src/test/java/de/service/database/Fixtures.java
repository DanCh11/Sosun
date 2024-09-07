package de.service.database;

import java.util.Arrays;
import java.util.List;

/**
 * Class that provides mock data for testing purposes.
 */
public final class Fixtures {

  public static final Contact bobsContact = new Contact(1L, "+49123123122");
  public static final Contact billysContact = new Contact(2L, "+491237631321");
  public static final Contact billysNewContact = new Contact(2L, "+491237631333");

  public static final List<Contact> bobsAndBillysContacts = Arrays.asList(
          bobsContact,
          billysContact
  );

}
