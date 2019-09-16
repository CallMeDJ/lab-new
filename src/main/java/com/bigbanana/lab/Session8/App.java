package com.bigbanana.lab.Session8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.bigbanana.lab.Session8")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
