package de.daycu.sosun.repositories;

import de.daycu.sosun.models.ContactPhoneNumber;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<ContactPhoneNumber, Long> { }
