package de.service.filereader;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "contact")
public class Contact {
  @JacksonXmlProperty(localName = "id")
  private Long id;
  @JacksonXmlProperty(localName = "phoneNumber")
  private String phoneNumber;
}
