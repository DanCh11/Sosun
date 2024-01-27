package de.daycu.sosun.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.daycu.sosun.controllers.PhoneNumberController;
import de.daycu.sosun.helpers.CSVHelper;
import de.daycu.sosun.models.PhoneNumber;
import de.daycu.sosun.services.PhoneNumberService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.Collectors;

import static de.daycu.sosun.utils.Fixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(PhoneNumberController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhoneNumberControllerTest {

    private static final String EXPECTED_PHONE_NUMBER = "+4917676478693";
    private static final String JSON_PATH = "$[0].phoneNumber";

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
    public void addPhoneNumberTest() throws Exception {
        when(phoneNumberService.addPhoneNumber(phoneNumber)).thenReturn(phoneNumber);
        final String content = objectMapper.writeValueAsString(phoneNumber);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/addPhoneNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void addPhoneNumbersTest() throws Exception {
        when(phoneNumberService.addPhoneNumbers(csvWithPhoneNumbers())).thenReturn(phoneNumbers);
        Iterable<PhoneNumber> phoneNumbers = CSVHelper.csvToPhoneNumbers(csvWithPhoneNumbers().getInputStream());
        final String content = objectMapper.writeValueAsString(phoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders
                .multipart("/addPhoneNumbers")
                .file(csvWithPhoneNumbers().getName(), csvWithPhoneNumbers().getBytes())
//                .post("/addPhoneNumbers")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.APPLICATION_JSON))


//                .content(content))
                .andExpect(status().isOk())
                .andDo(print());
//                .andExpect(jsonPath(JSON_PATH, is(EXPECTED_PHONE_NUMBER)));
//                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    public void findAllPhoneNumbersTest() throws Exception {
        when(phoneNumberService.findAll()).thenReturn(phoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/phoneNumbers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath(JSON_PATH, is(EXPECTED_PHONE_NUMBER)));
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

    private List<Long> extractIds(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream()
                .map(PhoneNumber::getId)
                .collect(Collectors.toList());
    }

}
