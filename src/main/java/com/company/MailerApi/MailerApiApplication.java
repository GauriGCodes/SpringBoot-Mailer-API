package com.company.MailerApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailerApiApplication.class, args);
	}

}
