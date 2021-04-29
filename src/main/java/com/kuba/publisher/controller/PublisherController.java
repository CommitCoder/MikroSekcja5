package com.kuba.publisher.controller;

import com.kuba.publisher.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {

    private final NotificationService notificationService;

    public PublisherController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public String sendStudentNotification(@RequestParam Long studentId){
        notificationService.sendStudentNotification(studentId);

        return "Message was sent to student with id: " + studentId;

    }

    @GetMapping("/test")
    public String testMethod(){

        return "Message was sent to student with id: ";

    }










//    private final RabbitTemplate rabbitTemplate;
//
//    public PublisherController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    //    @GetMapping("/message")
//    public String sendMessage(@RequestParam  String message){
//
//        // default exchange type  ->  routingKey = nazwa kolejki na którę będzie wrzucał nasz exchange
//        rabbitTemplate.convertAndSend("kurs", message);
//        return "Message was send to RabbitMq";
//    }

//    @PostMapping("/notification")
//    public String sendNotification(@RequestBody Notification notification){
//        rabbitTemplate.convertAndSend("kurs", notification);
//        return "Notification send";
//    }



}
