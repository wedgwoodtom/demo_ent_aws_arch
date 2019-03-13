package com.example.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.cache.memcached.MemcachedCacheFactory;
import org.springframework.cloud.aws.cache.memcached.SimpleSpringMemcached;
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
        // TODO: Maybe do this as a Lambda

        logger.info("Received Message:"+message+ " from:"+senderId);
        MediaNotification notification = MediaNotification.from(message);

        // TEST - GET THIS WORKING WITH SPRING CACHE

        logger.info("Writing to Cache...");
        MemcachedCacheFactory factory = new MemcachedCacheFactory();
        try
        {
            SimpleSpringMemcached memCache = factory.createCache("media", "media.sodexk.cfg.usw2.cache.amazonaws.com", 11211);
            logger.info("--> put...");
            memCache.put("key", message);
            logger.info("done.");
            logger.info("<-- get...");
            memCache.get("key");
            logger.info("done.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Query Data (put in Cache)
//        dataService.getMediaById(notification.getId());

        // Save computed data to Dynamo - just anything for now
        logger.info("Save Computed Data...");
        dynamoDBMapper.save(notification);
        logger.info("Saved");
    }


}
