package de.service.database;

import java.util.List;

public interface ContactOperations {
  Contact addContact(Contact contact);
  List<Contact> addContacts(List<Contact> contacts);
  List<Contact> updateContacts(List<Contact> contacts);
  void deleteContacts(List<Contact> contacts);
  void deleteContactById(Long id);
}
