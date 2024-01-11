package de.daycu.sosun.services;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EncryptionService {

    @Autowired
    private StringEncryptor encryptor;

    public String encrypt(String entity) {
        return encryptor.encrypt(entity);
    }

    public String decrypt(String entity) {
        return encryptor.decrypt(entity);
    }

    public Iterable<String> encryptAll(Iterable<String> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(encryptor::encrypt)
                .collect(Collectors.toList());
    }

    public Iterable<String> decryptAll(Iterable<String> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(encryptor::decrypt)
                .collect(Collectors.toList());
    }
}
