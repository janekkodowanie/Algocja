package io.github.janekkodowanie.ezML;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
public class EzMLApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzMLApplication.class, args);
	}
}
