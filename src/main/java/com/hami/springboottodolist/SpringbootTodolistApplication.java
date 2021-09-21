package com.hami.springboottodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootTodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTodolistApplication.class, args);
	}

}
