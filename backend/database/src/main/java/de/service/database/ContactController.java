package de.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @PostMapping("/add-contact")
  public Contact addContact(@RequestBody Contact contact) {
    return contactService.addContact(contact);
  }

  @PostMapping("/add-contacts")
  public List<Contact> addContacts(@RequestBody List<Contact> contacts) {
    return contactService.addContacts(contacts);
  }

  @PutMapping("/update-contacts")
  public List<Contact> updateContacts(@RequestBody List<Contact> contacts) {
    return contactService.updateContacts(contacts);
  }

  @DeleteMapping("/delete-contacts/{contacts}")
  public void deleteContacts(@PathVariable List<Contact> contacts) {
    contactService.deleteContacts(contacts);
  }

  @DeleteMapping("/delete-contact/{id}")
  public void deleteContactById(@PathVariable Long id) {
    contactService.deleteContactById(id);
  }
}
