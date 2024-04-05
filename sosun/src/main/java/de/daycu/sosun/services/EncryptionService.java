package de.daycu.sosun.services;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service that encrypts a given string object.
 */
@Service
public class EncryptionService {

    private BasicTextEncryptor encryptor;

    public EncryptionService(@Value("${jasypt.encryptor.password}") String encryptionPassword) {
        this.encryptor = new BasicTextEncryptor();
        encryptor.setPassword(encryptionPassword);
    }

    public String encrypt(String entity) {
        return encryptor.encrypt(entity);
    }

    public String decrypt(String entity) {
        return encryptor.decrypt(entity);
    }

}
