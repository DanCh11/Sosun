package de.daycu.sosun.services;

import de.daycu.sosun.helpers.CSVHelper;
import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private EncryptionService encryptionService;


    public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
        String encryptedPhoneNumber = encryptionService.encrypt(phoneNumber.getPhoneNumber());
        phoneNumber.setPhoneNumber(encryptedPhoneNumber);

        return phoneNumberRepository.save(phoneNumber);
    }

    public Iterable<PhoneNumber> addPhoneNumbers(MultipartFile file) throws IOException {
        if (CSVHelper.hasCSVFormat(file)) {
            Iterable<PhoneNumber> phoneNumbers = CSVHelper.csvToPhoneNumbers(file.getInputStream());

            List<String> plainPhoneNumbers = StreamSupport.stream(phoneNumbers.spliterator(), false)
                    .map(PhoneNumber::getPhoneNumber)
                    .toList();

            Iterable<String> encryptedPhoneNumbers = encryptionService.encryptAll(plainPhoneNumbers);

            List<PhoneNumber> encryptedPhoneNumbersList = StreamSupport.stream(phoneNumbers.spliterator(), false)
                    .map(phoneNumber -> {
                        PhoneNumber encryptedPhoneNumber = new PhoneNumber();
                        encryptedPhoneNumber.setPhoneNumber(encryptedPhoneNumbers.iterator().next());

                        return encryptedPhoneNumber;
                    }).toList();

//            for (PhoneNumber phoneNumber : phoneNumbers) {
//                String encryptedNumber = encryptionService.encrypt(phoneNumber.getPhoneNumber());
//                phoneNumber.setPhoneNumber(encryptedNumber);
//            }

            return phoneNumberRepository.saveAll(encryptedPhoneNumbersList);

        } else {
            throw new UnsupportedEncodingException("Only CSV files are supported.");
        }

    }

    public Iterable<PhoneNumber> findAll() {
        Iterable<PhoneNumber> phoneNumbers = phoneNumberRepository.findAll();
        for (PhoneNumber phoneNumber : phoneNumbers) {
            String decryptedNumber = encryptionService.decrypt(phoneNumber.getPhoneNumber());
            phoneNumber.setPhoneNumber(decryptedNumber);
        }

        return phoneNumbers;
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
