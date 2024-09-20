package de.service.database;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class ContactController {

  private ContactService contactService;

  @PostMapping("/add-contact")
  public Contact addContact(@RequestBody Contact contact) {
    return contactService.addContact(contact);
  }

  @PostMapping("/add-contacts")
  public List<Contact> addContacts(@RequestBody List<Contact> contacts) {
    return contactService.addContacts(contacts);
  }

  @GetMapping("/contacts")
  public List<Contact> getContacts() {
    return contactService.getContacts();
  }

  @DeleteMapping("/delete-contacts")
  public void deleteContacts(@RequestBody List<Long> ids) {
    contactService.deleteContacts(ids);
  }

  @DeleteMapping("/delete-contact/{id}")
  public void deleteContactById(@PathVariable("id") Long id) {
    contactService.deleteContactById(id);
  }
}
