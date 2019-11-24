package com.bookstore.BookStore.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RuiBookStore {

	public static void main(String[] args) {
		SpringApplication.run(RuiBookStore.class, args);
	}

}
