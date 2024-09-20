package de.service.database;

import static de.service.database.Fixtures.billysContact;
import static de.service.database.Fixtures.bobsAndBillysContacts;
import static de.service.database.Fixtures.bobsAndBillysIds;
import static de.service.database.Fixtures.bobsContact;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

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
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContactService contactService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testAddContact() throws Exception {
		given(contactService.addContact(any(Contact.class))).willReturn(bobsContact);

		ResultActions response = mockMvc.perform(post("/add-contact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bobsContact)));

		response.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.phoneNumber", is(bobsContact.getPhoneNumber())));

	}

	@Test
	void testAddContacts() throws Exception {
		given(contactService.addContacts(anyList())).willReturn(bobsAndBillysContacts);

		ResultActions response = mockMvc.perform(post("/add-contacts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bobsAndBillysContacts)));

		response.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.[0].phoneNumber", is(bobsContact.getPhoneNumber())))
				.andExpect(jsonPath("$.[1].phoneNumber", is(billysContact.getPhoneNumber())));
	}

	@Test
	void testGetContacts() throws Exception {
		given(contactService.getContacts()).willReturn(bobsAndBillysContacts);

		ResultActions response = mockMvc.perform(get("/contacts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bobsAndBillysContacts)));

		response.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.[0].phoneNumber",
						is(bobsContact.getPhoneNumber())))
				.andExpect(jsonPath("$.[1].phoneNumber",
						is(billysContact.getPhoneNumber())));
	}

	@Test
	void testDeleteContactById() throws Exception {
		willDoNothing().given(contactService).deleteContactById(
				bobsContact.getId());

		ResultActions response = mockMvc.perform(
				delete("/delete-contact/{id}", bobsContact.getId()));

		response.andExpect(status().is2xxSuccessful());
	}

	@Test
	void testDeleteContacts() throws Exception {
		willDoNothing().given(contactService).deleteContacts(bobsAndBillysIds);

		ResultActions response = mockMvc.perform(
				delete("/delete-contacts", bobsAndBillysIds)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bobsAndBillysIds)));

		response.andExpect(status().is2xxSuccessful());
	}

}
