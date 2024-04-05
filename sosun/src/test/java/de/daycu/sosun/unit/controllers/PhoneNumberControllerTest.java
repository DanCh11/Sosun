package de.daycu.sosun.unit.controllers;

import static de.daycu.sosun.utils.Fixtures.csvWithPhoneNumbers;
import static de.daycu.sosun.utils.Fixtures.phoneNumber;
import static de.daycu.sosun.utils.Fixtures.phoneNumbers;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.daycu.sosun.controllers.PhoneNumberController;
import de.daycu.sosun.helpers.CSVHelper;
import de.daycu.sosun.models.ContactPhoneNumber;
import de.daycu.sosun.services.PhoneNumberService;

@AutoConfigureMockMvc
@WebMvcTest(PhoneNumberController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private PhoneNumberService phoneNumberService;
    @InjectMocks
    private PhoneNumberController phoneNumberController;

    ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        this.mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController).build();
    }

    @Test
    public void addPhoneNumbersTest() throws Exception {
        System.out.println("CSV: " + csvWithPhoneNumbers());

        when(phoneNumberService.addPhoneNumbers(csvWithPhoneNumbers())).thenReturn(phoneNumbers);
        Iterable<ContactPhoneNumber> phoneNumbers = CSVHelper.csvToPhoneNumbers(csvWithPhoneNumbers().getInputStream());
        final String content = objectMapper.writeValueAsString(phoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders
                .multipart("/addPhoneNumbers")
                .file(csvWithPhoneNumbers().getName(), csvWithPhoneNumbers().getBytes())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void findAllPhoneNumbersTest() throws Exception {
        when(phoneNumberService.findAll()).thenReturn(phoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/phoneNumbers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void deletePhoneNumberTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deletePhoneNumberById/{id}", phoneNumber.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(phoneNumberService, times(1)).deletePhoneNumberById(phoneNumber.getId());
    }

    @Test
    public void deletePhoneNumberListById() throws Exception {
        List<Long> ids = extractIds(phoneNumbers);

        String idsString = ids.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deletePhoneNumberListById/{ids}", idsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(phoneNumberService, times(1)).deletePhoneNumberListById(ids);
    }

    private List<Long> extractIds(List<ContactPhoneNumber> phoneNumbers) {
        return phoneNumbers.stream()
                .map(ContactPhoneNumber::getId)
                .collect(Collectors.toList());
    }

}
