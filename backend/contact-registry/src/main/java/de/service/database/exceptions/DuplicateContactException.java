package de.service.database.exceptions;

public class DuplicateContactException extends RuntimeException{
  public DuplicateContactException(String message) {
    super(message);
  }
}
