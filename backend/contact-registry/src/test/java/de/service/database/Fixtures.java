package de.service.database;

import java.util.Arrays;
import java.util.List;

/**
 * Class that provides mock data for testing purposes.
 */
public final class Fixtures {

  public static final Contact bobsContact = Contact.builder()
      .id(1L)
      .phoneNumber("+49123123122")
      .build();

  public static final Contact billysContact = Contact.builder()
      .id(2L)
      .phoneNumber("+491237631321")
      .build();

  public static final Contact billysNewContact = Contact.builder()
      .id(2L)
      .phoneNumber("+491237631333")
      .build();

  public static final List<Contact> bobsAndBillysContacts = Arrays.asList(
      bobsContact,
      billysContact);

  public static final List<Long> bobsAndBillysIds = Arrays.asList(
      bobsContact.getId(),
      billysContact.getId());
}
