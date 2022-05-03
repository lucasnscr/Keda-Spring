package com.lucasnscr.eventservicereceiver.controller;

import com.lucasnscr.eventservicereceiver.model.Message;
import com.lucasnscr.eventservicereceiver.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    public static final String KEDAQ = "kedaQ";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/receiveMessage")
    public List<Message> receive(){
        Iterable<Message> allMessage = messageRepository.findAll();
        return StreamSupport.stream(allMessage.spliterator(), false).collect(Collectors.toList());
    }
}
