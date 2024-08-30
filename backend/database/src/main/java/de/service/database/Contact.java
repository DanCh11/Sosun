package de.service.database;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Contact {
  private Long id;
  private String phoneNumber;

}
