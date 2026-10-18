package com.yusufcandmrz.chronicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ChronicleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChronicleApplication.class, args);
	}

}
