package de.daycu.sosun.repositories;

import de.daycu.sosun.models.PhoneNumber;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> { }
