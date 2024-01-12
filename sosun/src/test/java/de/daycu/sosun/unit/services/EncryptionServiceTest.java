package de.daycu.sosun.unit.services;

import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.services.EncryptionService;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static de.daycu.sosun.utils.Fixtures.phoneNumber;
import static de.daycu.sosun.utils.Fixtures.phoneNumbers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class EncryptionServiceTest {

    @Autowired
    private StringEncryptor encryptor;
    @Autowired
    private EncryptionService encryptionService;

    @Test
    public void testEncryptAndDecryptString() {
        final String input = phoneNumber.getPhoneNumber();
        String encryptedResult = encryptionService.encrypt(input);
        String decryptedResult = encryptionService.decrypt(encryptedResult);

        assertNotEquals(input, encryptedResult);
        assertEquals(input, decryptedResult);
    }

    @Test
    public void testEncryptAndDecryptAll() {
        List<String> inputList = phoneNumbers.stream().map(PhoneNumber::getPhoneNumber).collect(Collectors.toList());
        Iterable<String> encryptedResult = encryptionService.encryptAll(inputList);
        Iterable<String> decryptedResult = encryptionService.decryptAll(encryptedResult);

        assertNotEquals(inputList, encryptedResult);
        assertEquals(inputList, decryptedResult);
    }

}
