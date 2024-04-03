package de.daycu.sosun.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import de.daycu.sosun.models.ContactPhoneNumber;

@Service
public class TwilioService {
    
    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.number}")
    private String twilioPhoneNumber;

    @Autowired
    private PhoneNumberService phoneNumberService;

    TwilioService() {
        Twilio.init(twilioSid, twilioAuthToken);
    }

    public void sendSMS(String message) {
        Iterable<ContactPhoneNumber> phoneNumbers = phoneNumberService.findAll();
        for (ContactPhoneNumber phoneNumber : phoneNumbers) {
            Message.creator(new PhoneNumber(phoneNumber.getPhoneNumber()), twilioPhoneNumber, message);
        }
    }
}
