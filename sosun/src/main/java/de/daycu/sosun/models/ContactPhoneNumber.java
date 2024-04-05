package de.daycu.sosun.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "PhoneNumbers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactPhoneNumber {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        @NonNull
        Long id;

        @Column(name = "PhoneNumber")
        @NonNull
        String phoneNumber;
}
