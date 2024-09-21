package de.service.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
  @Id
  @SequenceGenerator(name = "contact_id_sequence", sequenceName = "contact_id_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_sequence")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "phoneNumber", nullable = false, unique = true)
  private String phoneNumber;

}
