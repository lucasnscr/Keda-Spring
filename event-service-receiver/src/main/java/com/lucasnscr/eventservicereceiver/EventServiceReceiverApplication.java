package com.lucasnscr.eventservicereceiver;

import com.lucasnscr.eventservicereceiver.model.Message;
import com.lucasnscr.eventservicereceiver.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class EventServiceReceiverApplication {

	public static final String KEDAQ = "kedaQ";

	public static void main(String[] args) {
		SpringApplication.run(EventServiceReceiverApplication.class, args);
	}

	@Autowired
	public MessageRepository msgRepo;


	@Bean
	public Queue kedaQueue() {
		return new Queue(KEDAQ, false);
	}

	@RabbitListener(queues = KEDAQ)
	public void listen(String message) {
		log.info("Message received from kedaQ : " + message);
		msgRepo.save(new Message(message));
	}

}
