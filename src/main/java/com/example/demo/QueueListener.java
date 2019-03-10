package com.example.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class QueueListener
{
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @SqsListener(value = "${media.notification.queue}")
    public void receiveMessage(String message, @Header("SenderId") String senderId)
    {
        logger.info("Received Message:"+message+ " from:"+senderId);

        // Put in Cache


        // Save Notification to Dynamo
        MediaNotification notification = MediaNotification.from(message);
        dynamoDBMapper.save(notification);
    }

}
