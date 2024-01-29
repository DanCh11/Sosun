package de.daycu.sosun.controllers;

import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.services.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @PostMapping("/addPhoneNumbers")
    public Iterable<PhoneNumber> addPhoneNumbers(@RequestBody MultipartFile file) throws IOException {
        return phoneNumberService.addPhoneNumbers(file);
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
