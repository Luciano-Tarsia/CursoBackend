package com.coderhouse.h2;

import com.coderhouse.h2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class H2Application {

	public static void main(String[] args) {
		SpringApplication.run(H2Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository repository) {
		return (args) -> {
			repository.save(User.builder().name("Jose").age(24).build());
			repository.save(User.builder().name("Esteban").age(28).build());
			repository.save(User.builder().name("Juan").age(26).build());
			repository.save(User.builder().name("Matias").age(27).build());
			repository.save(User.builder().name("Nina").age(19).build());
			repository.save(User.builder().name("Camila").age(20).build());
		};
	}

	}