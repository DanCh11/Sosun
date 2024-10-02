package de.service.fileuploader;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload-dir";
}
