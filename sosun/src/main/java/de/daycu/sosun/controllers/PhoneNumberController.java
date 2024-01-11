package de.daycu.sosun.controllers;

import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping("/addPhoneNumber")
    public PhoneNumber addPhoneNumber(@RequestBody PhoneNumber phoneNumber) {
        return phoneNumberService.addPhoneNumber(phoneNumber);
    }

    @PostMapping("/addPhoneNumbers")
    public Iterable<PhoneNumber> addPhoneNumbers(@RequestBody Iterable<PhoneNumber> phoneNumbers) {
        return phoneNumberService.addPhoneNumbers(phoneNumbers);
    }

    @GetMapping("/phoneNumbers")
    public Iterable<PhoneNumber> findAll() {
        return phoneNumberService.findAll();
    }

    @DeleteMapping(path = "deletePhoneNumberById/{id}")
    public void deletePhoneNumberById(@PathVariable Long id) {
        phoneNumberService.deletePhoneNumberById(id);
    }

    @DeleteMapping(path = "deletePhoneNumberListById/{ids}")
    public void deletePhoneNumberListById(@PathVariable List<Long> ids) {
        phoneNumberService.deletePhoneNumberListById(ids);
    }
}
