package com.shailendra.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.shailendra")
@EnableJpaRepositories("com.shailendra.repo")
@EntityScan("com.shailendra.pojo")
@EnableScheduling
public class CDRSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CDRSystemApplication.class, args);
	}
}
