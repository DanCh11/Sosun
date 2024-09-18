package de.service.database;

import de.service.database.exceptions.ContactNotFoundException;
import de.service.database.exceptions.DuplicateContactException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

  private final ContactRepository contactRepository;

  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

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

  public Contact updateContact(Long id, Contact contactDetails) {
    Contact updatedContact = contactRepository.findById(id).orElseThrow();
    updatedContact.setPhoneNumber(contactDetails.getPhoneNumber());

    return contactRepository.save(updatedContact);
  }

  public void deleteContacts(List<Contact> contacts) {
    try {
      contactRepository.deleteAll(contacts);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while deleting all contacts.", e);
    }
  }

  public void deleteContactById(Long id) throws ContactNotFoundException, DataAccessException {
    try {
      contactRepository.deleteById(id);

    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while deleting contact by id.", e);
    }
  }

  public Contact findContactById(Long id) {
      return contactRepository.findById(id).orElseThrow();
  }

  public List<Contact> findAllContacts() {
    try {
      return contactRepository.findAll();
    } catch (DataAccessException e) {
      throw new RuntimeException("Error accessing data while finding all contacts.", e);
    }
  }

}
