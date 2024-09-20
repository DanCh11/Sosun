package de.service.database;

import static de.service.database.Fixtures.bobsContact;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ContactController.class)
class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ContactService contactService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @Order(1)
  void testAddContact() throws Exception {
    given(contactService.addContact(any(Contact.class))).willReturn(bobsContact);

    ResultActions response = mockMvc.perform(post("/add-contact")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(bobsContact)));

    response.andDo(print())
            .andExpect(status().isCreated());

  }

}
