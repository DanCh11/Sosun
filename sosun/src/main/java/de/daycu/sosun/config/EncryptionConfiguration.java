package de.daycu.sosun.config;

import de.daycu.sosun.services.EncryptionService;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfiguration {

    @Value("${jasypt.encryptor.password}")
    private String encryptionPassword;

    @Value("${jasypt.encryptor.algorithm}")
    private String encryptionAlgorithm;

    @Bean("stringEncryptor")
    public StringEncryptor stringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionPassword);
        encryptor.setAlgorithm(encryptionAlgorithm);

        return encryptor;
    }
}
