package com.jfloresdev.java.ws.api_books_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication()
@RestController
public class ApiBooksV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiBooksV1Application.class, args);
	}



}
