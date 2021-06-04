package ru.sfedu.diplomabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.sfedu"})
public class  DiplomaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiplomaBackendApplication.class, args);
	}

}
