package de.daycu.sosun.utils;

import de.daycu.sosun.models.PhoneNumber;

import java.util.Arrays;
import java.util.List;

public final class Fixtures {

    public static final PhoneNumber phoneNumber = new PhoneNumber(1L, "+4917676478693");

    public static final List<PhoneNumber> phoneNumbers = Arrays.asList(
        new PhoneNumber(1L, "+4917676478693"),
        new PhoneNumber(2L, "+491234567890")
    );
}
