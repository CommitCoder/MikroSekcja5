package com.kuba.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired // ale nie musi tego być, konstruktor nie wymaga adnotacji @Autowired
    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/message")
    public String sendMessage(@RequestParam  String message){

        // default exchange type  ->  routingKey = nazwa kolejki na którę będzie wrzucał nasz exchange
        rabbitTemplate.convertAndSend("kurs", message);
        return "Message was send to RabbitMq";
    }

}
