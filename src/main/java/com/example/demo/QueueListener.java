package com.example.demo;

import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class QueueListener
{
    @SqsListener("media-notification-queue")
    public void receiveMessage(String message, @Header("SenderId") String senderId)
    {
        // DO SOME STUFF
    }

}
