package com.lucasnscr.eventservicesender.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sender")
@Slf4j
public class SenderController {

    public static final String KEDAQ = "kedaQ";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public void send(@RequestParam("msg") String msg){
        log.info("Send Message: {}", msg);
        rabbitTemplate.convertAndSend(KEDAQ, msg);
    }

}
