package de.daycu.sosun.services;

import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public Iterable<PhoneNumber> addPhoneNumbers(Iterable<PhoneNumber> phoneNumbers) {
        return phoneNumberRepository.saveAll(phoneNumbers);
    }

    public Iterable<PhoneNumber> findAll() {
        return phoneNumberRepository.findAll();
    }

    public void deletePhoneNumberById(Long id) {
        phoneNumberRepository.deleteById(id);
    }

    public void deletePhoneNumberListById(List<Long> ids) {
        phoneNumberRepository.deleteAllById(ids);
    }

    public void deleteAllPhoneNumbers() {
        phoneNumberRepository.deleteAll();
    }

}
