package com.cg.capstore.capstoreapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.cg.capstore")
@EnableJpaRepositories("com.cg.capstore.dao")
@EntityScan("com.cg.capstore.beans")
public class CapStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapStoreApplication.class, args);
	}
}
