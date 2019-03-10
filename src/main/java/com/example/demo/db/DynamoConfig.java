package com.example.demo.db;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoConfig
{
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonDynamoDB amazonDynamoDB()
    {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.fromName(region))
//                .withRegion(Regions.US_WEST_2)
//                .withCredentials(new ProfileCredentialsProvider("myProfile"))
                .build();

        return amazonDynamoDB;
    }

    @Bean
    DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB)
    {
        DynamoDBMapper mapper = new DynamoDBMapper(amazonDynamoDB);
        return mapper;
    }

}
