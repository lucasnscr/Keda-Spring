package com.lucasnscr.eventservicesender;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventServiceSenderApplication {

	public static final String KEDAQ = "kedaQ";

	public static void main(String[] args) {
		SpringApplication.run(EventServiceSenderApplication.class, args);
	}

	@Bean
	public Queue kedaQueue() {
		return new Queue(KEDAQ, false);
	}

}
