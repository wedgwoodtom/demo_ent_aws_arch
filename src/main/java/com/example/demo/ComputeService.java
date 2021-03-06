package com.example.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;


@RestController
public class ComputeService
{
    private static final Logger logger = LoggerFactory.getLogger(ComputeService.class);


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public LoginResponse login()
    {
        // TODO: DO something
        return new LoginResponse("Some Data");
    }

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private DataService dataService;


    @SqsListener(value = "${media.notification.queue}")
    public void receiveMessage(String message, @Header("SenderId") String senderId)
    {
        logger.info("<-- Queue Message:"+message+ " from:"+senderId);
        MediaNotification notification = MediaNotification.from(message);

        logger.info("--> Pre-Computing...");
        // Refresh media associated with the notification
        dataService.updateMedia(notification.getId());
        dataService.getMediaById(notification.getId());         // Just to see that it was cached
        // Recompute data...

        // Save computed data to Dynamo - just anything for now
        logger.info("--> Saving Computed Results...");
        dynamoDBMapper.save(notification);
        logger.info("    Saved");
    }


}
