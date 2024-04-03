package de.daycu.sosun.models;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class TwilioClient {

    @NonNull private String accountId;
    @NonNull private String authToken;
    @NonNull private List<ContactPhoneNumber> phoneNumbers;
}
