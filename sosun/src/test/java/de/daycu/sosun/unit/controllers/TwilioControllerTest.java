package de.daycu.sosun.unit.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.daycu.sosun.controllers.TwilioController;
import de.daycu.sosun.services.TwilioService;

@SpringBootTest
@AutoConfigureMockMvc
public class TwilioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TwilioService twilioService;

    @InjectMocks
    private TwilioController twilioController;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(twilioController).build();
    }

    @Test
    public void testSendSMS() throws Exception {
        String message = "Test message";

        doNothing().when(twilioService).sendSMS(message);

        mockMvc.perform(MockMvcRequestBuilders.get("/sms")
                .content(message)
                .contentType("text/plain"))
                .andExpect(status().isOk());

        verify(twilioService).sendSMS(message);
    }
}
