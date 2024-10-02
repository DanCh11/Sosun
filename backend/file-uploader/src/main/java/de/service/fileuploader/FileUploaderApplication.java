package de.service.fileuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploaderApplication.class, args);
	}

}
