package de.daycu.sosun.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PhoneNumbers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneNumber {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        Long id;
        @Column(name = "PhoneNumber")
        String phoneNumber;
}
