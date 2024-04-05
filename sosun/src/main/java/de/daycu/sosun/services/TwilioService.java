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
    
    private String twilioSid;
    private String twilioAuthToken;
    private String twilioPhoneNumber;

    @Autowired
    private PhoneNumberService phoneNumberService;

    public TwilioService(
        @Value("${twilio.sid}") String twilioSid,
        @Value("${twilio.auth.token}") String twilioAuthToken,
        @Value("${twilio.number}") String twilioPhoneNumber) {
        
        this.twilioSid = twilioSid;
        this.twilioAuthToken = twilioAuthToken;
        this.twilioPhoneNumber = twilioPhoneNumber;

        Twilio.init(twilioSid, twilioAuthToken);
    }

    public void sendSMS(String message) {
        Iterable<ContactPhoneNumber> phoneNumbers = phoneNumberService.findAll();
        for (ContactPhoneNumber phoneNumber : phoneNumbers) {
            Message.creator(new PhoneNumber(phoneNumber.getPhoneNumber()), twilioPhoneNumber, message);
        }
    }
}
