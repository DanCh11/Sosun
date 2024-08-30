package de.service.database;

import de.service.database.exceptions.ContactNotFoundException;
import de.service.database.exceptions.DuplicateContactException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements ContactOperations {

  @Autowired
  private ContactRepository contactRepository;

  @Override
  public Contact addContact(Contact contact) {
    try {
      if (contactRepository.existsByPhoneNumber(contact.getPhoneNumber())) {
        throw new DuplicateContactException("This Phone Number: " + contact.getPhoneNumber() + " Already Exists");
      }
      return contactRepository.save(contact);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while adding contacts.", e);
    }
  }

  @Override
  public List<Contact> addContacts(List<Contact> contacts) {
    try {
      for (Contact contact : contacts) {
        if (contactRepository.existsByPhoneNumber(contact.getPhoneNumber())) {
          throw new DuplicateContactException("This Phone Number: " + contact.getPhoneNumber() + " Already Exists");
        }
      }
      return contactRepository.saveAll(contacts);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while adding contacts.", e);
    }
  }

  @Override
  public List<Contact> updateContacts(List<Contact> contacts) {
    try {
      for (Contact contact : contacts) {
        if (!contactRepository.existsById(contact.getId())) {
          throw new ContactNotFoundException("Contact with id " + contact.getId() + " not found.");
        }
      }
      return contactRepository.saveAll(contacts);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while updating contact.", e);
    }
  }

  @Override
  public void deleteContacts(List<Contact> contacts) {
    try {
      contactRepository.deleteAll(contacts);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while deleting all contacts.", e);
    }
  }

  @Override
  public void deleteContactById(Long id) throws ContactNotFoundException, DataAccessException {
    try {
      contactRepository.deleteById(id);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while deleting contact by id.", e);
    }
  }
}
