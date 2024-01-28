package de.daycu.sosun.services;

import de.daycu.sosun.helpers.CSVHelper;
import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CSVService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public void save(MultipartFile file) {
        try {
            Iterable<PhoneNumber> phoneNumbers = CSVHelper.csvToPhoneNumbers(file.getInputStream());

            phoneNumberRepository.saveAll(phoneNumbers);

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public Iterable<PhoneNumber> findAll() {
        return phoneNumberRepository.findAll();
    }

}
