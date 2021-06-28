package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		try {
			System.out.println("--"+org.hibernate.annotations.common.Version.getVersionString());
			SpringApplication.run(DemoApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
