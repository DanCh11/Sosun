package de.daycu.sosun.unit.services;

import static de.daycu.sosun.utils.Fixtures.phoneNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.daycu.sosun.services.EncryptionService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EncryptionServiceTest {

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

}
