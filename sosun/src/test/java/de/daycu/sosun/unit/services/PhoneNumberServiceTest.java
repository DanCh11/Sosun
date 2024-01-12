package de.daycu.sosun.unit.services;

import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.repositories.PhoneNumberRepository;
import de.daycu.sosun.services.PhoneNumberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static de.daycu.sosun.utils.Fixtures.phoneNumber;
import static de.daycu.sosun.utils.Fixtures.phoneNumbers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhoneNumberServiceTest {

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberService phoneNumberService;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void addPhoneNumberTest() {
        when(phoneNumberRepository.save(phoneNumber)).thenReturn(phoneNumber);
        PhoneNumber result = phoneNumberService.addPhoneNumber(phoneNumber);
        verify(phoneNumberRepository, times(1)).save(phoneNumber);

        assertEquals(phoneNumber, result);
    }

    @Test
    public void addPhoneNumbersTest() {
        when(phoneNumberRepository.saveAll(phoneNumbers)).thenReturn(phoneNumbers);
        Iterable<PhoneNumber> result = phoneNumberService.addPhoneNumbers(phoneNumbers);
        verify(phoneNumberRepository, times(1)).saveAll(phoneNumbers);

        assertEquals(phoneNumbers, result);
    }

    @Test
    public void findAllTest() {
        when(phoneNumberRepository.findAll()).thenReturn(phoneNumbers);
        Iterable<PhoneNumber> result = phoneNumberService.findAll();
        verify(phoneNumberRepository, times(1)).findAll();

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
