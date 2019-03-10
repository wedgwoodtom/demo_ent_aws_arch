package com.example.demo;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationListener
{
    private final QueueMessagingTemplate queueMessagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    @Value("${media.notification.queue}")
    private String mediaQueueName;

    @Autowired
    public NotificationListener(AmazonSQSAsync amazonSqs)
    {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
    }

    public void send(String message)
    {
        logger.info("Sending message:"+logger);
        this.queueMessagingTemplate.send(mediaQueueName, MessageBuilder.withPayload(message).build());
    }

    @Scheduled(fixedDelayString = "30000")
    private void sendMessage()
    {
        String mediaId = Integer.toString((int)Math.random() * Integer.MAX_VALUE + 1);

        String message = "{\n" +
                "        \"$xmlns\": {\n" +
                "            \"media\": \"http://search.yahoo.com/mrss/\",\n" +
                "            \"pla\": \"http://xml.theplatform.com/data/object/admin\",\n" +
                "            \"plmedia\": \"http://xml.theplatform.com/media/data/Media\"\n" +
                "        },\n" +
                "        \"id\": 6596199989,\n" +
                "        \"method\": \"put\",\n" +
                "        \"type\": \"Media\",\n" +
                "        \"cid\": \"e157343f-c115-4713-baaa-e9e5493cba61\",\n" +
                "        \"entry\": {\n" +
                "            \"id\": \"http://data.media.theplatform.com/media/data/Media/"+mediaId+"\",\n" +
                "            \"updated\": 1512363274000,\n" +
                "            \"ownerId\": \"http://access.auth.theplatform.com/data/Account/2649321885\",\n" +
                "            \"updatedByUserId\": \"https://identity.auth.theplatform.com/idm/data/User/mpx/2744660\",\n" +
                "            \"guid\": \"AMC_TWD_807_DAI\",\n" +
                "            \"title\": \"Time for After\",\n" +
                "            \"pla$adminTags\": [\n" +
                "                \"LongformIngested\"\n" +
                "            ],\n" +
                "            \"plmedia$approved\": true,\n" +
                "            \"plmedia$originalOwnerIds\": [\n" +
                "                \n" +
                "            ],\n" +
                "            \"plmedia$originalMediaIds\": [\n" +
                "                \n" +
                "            ],\n" +
                "            \"plmedia$programId\": \"\",\n" +
                "            \"plmedia$seriesId\": \"\",\n" +
                "            \"plmedia$availabilityState\": \"available\"\n" +
                "        }\n" +
                "    }";

        // TODO: Just put anything there for now
        send(message);
    }

}
