package io.github.janekkodowanie.algocja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
public class AlgocjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgocjaApplication.class, args);
	}
}
