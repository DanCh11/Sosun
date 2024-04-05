package de.daycu.sosun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.daycu.sosun.services.TwilioService;

@RestController
public class TwilioController {
    
    @Autowired
    private TwilioService twilioService;

    @GetMapping("/sms")
    public void sendSMS(@RequestBody String message) {
        twilioService.sendSMS(message);
    }
}
