package de.daycu.sosun.unit.services;

import de.daycu.sosun.exceptions.UnsupportedFileFormatException;
import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.repositories.PhoneNumberRepository;
import de.daycu.sosun.services.EncryptionService;
import de.daycu.sosun.services.PhoneNumberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static de.daycu.sosun.utils.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PhoneNumberServiceTest {

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Mock
    private EncryptionService encryptionService;

    @InjectMocks
    private PhoneNumberService phoneNumberService;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addPhoneNumbersTest() throws UnsupportedFileFormatException, IOException {
        
        when(encryptionService.encrypt(anyString())).thenReturn("encryptedPhoneNumber");
        when(phoneNumberRepository.saveAll(anyIterable())).thenReturn(phoneNumbers);

        Iterable<PhoneNumber> result = phoneNumberService.addPhoneNumbers(csvWithPhoneNumbers());

        verify(encryptionService, times(phoneNumbers.size())).encrypt(anyString());
        verify(phoneNumberRepository, times(1)).saveAll(anyIterable());

        assertEquals(phoneNumbers, result);
    }

    @Test
    public void findAllTest() {
        when(phoneNumberRepository.findAll()).thenReturn(phoneNumbers);
        when(encryptionService.decrypt(anyString())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Iterable<PhoneNumber> result = phoneNumberService.findAll();

        verify(phoneNumberRepository, times(1)).findAll();
        verify(encryptionService, times(phoneNumbers.size())).decrypt(anyString());

        assertEquals(2, StreamSupport.stream(result.spliterator(), false).count());
    }

    @Test
    public void deletePhoneNumberTest() {
        phoneNumberService.deletePhoneNumberById(phoneNumber.getId());
        verify(phoneNumberRepository, times(1)).deleteById(phoneNumber.getId());
    }

    @Test
    public void deletePhoneNumberListTest() {
        List<Long> ids = extractIds(phoneNumbers);
        phoneNumberService.deletePhoneNumberListById(ids);
        verify(phoneNumberRepository, times(1)).deleteAllById(ids);
    }

    @Test
    public void deleteAllPhoneNumbersTest() {
        phoneNumberService.deleteAllPhoneNumbers();
        verify(phoneNumberRepository, times(1)).deleteAll();
    }

    private List<Long> extractIds(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream()
                .map(PhoneNumber::getId)
                .collect(Collectors.toList());
    }

}
