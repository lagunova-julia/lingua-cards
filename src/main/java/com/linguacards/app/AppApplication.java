package com.linguacards.app;

import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		log.info("=== APPLICATION STARTING ===");
		SpringApplication.run(AppApplication.class, args);
		log.info("=== APPLICATION STARTING ===");
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

}
