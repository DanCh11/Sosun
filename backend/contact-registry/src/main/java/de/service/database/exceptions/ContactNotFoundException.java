package de.service.database.exceptions;

public class ContactNotFoundException extends RuntimeException{
  public ContactNotFoundException(String message) {
    super(message);
  }
}
