package de.service.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static de.service.database.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ContactServiceTest {

  @Mock
  private ContactRepository repositoryMock;

  @InjectMocks
  private ContactService service;

  @BeforeEach
  void setup() {
    assertNotNull(repositoryMock);
    service = new ContactService(repositoryMock);
  }

  @Test
  void testAddContact() {
    when(repositoryMock.save(Mockito.any(Contact.class))).thenReturn(bobsContact);
    Contact expectedContact = service.addContact(bobsContact);

    assertEquals(expectedContact, bobsContact);
  }

  @Test
  void testAddContacts() {
    when(repositoryMock.saveAll(Mockito.any(List.class))).thenReturn(bobsAndBillysContacts);
    List<Contact> expectedContacts = service.addContacts(bobsAndBillysContacts);

    assertEquals(expectedContacts, bobsAndBillysContacts);
  }

  @Test
  void testUpdateContact() {
    when(repositoryMock.findById(billysContact.getId())).thenReturn(Optional.of(billysContact));
    when(repositoryMock.save(billysContact)).thenReturn(billysContact);
    Contact billysExpectedContact = service.updateContact(billysContact.getId(), billysNewContact);

    assertEquals(billysExpectedContact, billysNewContact);
  }

  @Test
  void testDeleteContacts() {
    final int NO_CONTACTS_LEFT = 0;

    doNothing().when(repositoryMock).deleteAll(bobsAndBillysContacts);
    service.deleteContacts(bobsAndBillysContacts);
    verify(repositoryMock, times(1)).deleteAll(bobsAndBillysContacts);

    assertEquals(service.findAllContacts().size(), NO_CONTACTS_LEFT);
  }

  @Test
  void testDeleteContactById() {
    final int NO_CONTACTS_LEFT = 0;

    doNothing().when(repositoryMock).deleteById(bobsContact.getId());
    service.deleteContactById(bobsContact.getId());
    verify(repositoryMock, times(1)).deleteById(bobsContact.getId());

    assertEquals(service.findAllContacts().size(), NO_CONTACTS_LEFT);
  }

  @Test
  void testFindContactById() {
    when(repositoryMock.findById(bobsContact.getId())).thenReturn(Optional.of(bobsContact));
    Contact bobsExpectedContact = service.findContactById(bobsContact.getId());

    assertEquals(bobsExpectedContact, bobsContact);
  }

  @Test
  void testFindAllContacts() {
    when(repositoryMock.findAll()).thenReturn(bobsAndBillysContacts);
    List<Contact> bobAndBillyExpectedContacts = service.findAllContacts();

    assertEquals(bobAndBillyExpectedContacts, bobsAndBillysContacts);
  }

}
