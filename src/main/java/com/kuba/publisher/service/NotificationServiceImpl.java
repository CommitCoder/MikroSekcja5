package com.kuba.publisher.service;

import com.kuba.publisher.model.Notification;
import com.kuba.publisher.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String URL_STUDENT_SERVICE = "http://localhost:8080/students/";
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendStudentNotification(Long studentId) {
        Student student = restTemplate.exchange(URL_STUDENT_SERVICE + studentId,
                HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();

        Notification notification = createNotification(student);
        rabbitTemplate.convertAndSend("kurs", notification);

    }

    private Notification createNotification(Student student) {
        Notification notification = new Notification();
        notification.setEmail(student.getEmail());
        notification.setTitle("Hello " + student.getFirstName());
        notification.setBody("Nice to have you here " + student.getFirstName() + " " + student.getLastName());
        return notification;
    }
}
