package com.school.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListenerTest {
    @RabbitListener(queues = "test")
    public void rabbitListener(Message message, String gireFriend){
        System.out.println(message.getBody()+"======="+message.getMessageProperties()+"-------"+message);
        System.out.println(gireFriend+"接受消息成功");
    }
}
