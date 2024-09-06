package de.service.database;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "contacts", uniqueConstraints = {@UniqueConstraint(columnNames = {"phoneNumber"})}
)
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "phoneNumber", nullable = false)
  private String phoneNumber;

}
