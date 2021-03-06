package com.example.demo.lambda;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

public class ComputeServiceHandler implements RequestHandler<SQSEvent, Void>
{
    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "Person";
    private Regions REGION = Regions.US_WEST_2;

    //SQS
    @Override
    public Void handleRequest(SQSEvent event, Context context)
    {
        for (SQSMessage msg : event.getRecords())
        {
            System.out.println(new String(msg.getBody()));
        }
        return null;
    }



    // DYNAMO

//    public PersonResponse handleRequest(
//        PersonRequest personRequest, Context context) {
//
//        this.initDynamoDbClient();
//
//        persistData(personRequest);
//
//        PersonResponse personResponse = new PersonResponse();
//        personResponse.setMessage("Saved Successfully!!!");
//        return personResponse;
//    }

//    private PutItemOutcome persistData(PersonRequest personRequest)
//        throws ConditionalCheckFailedException
//    {
//        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
//            .putItem(
//                new PutItemSpec().withItem(new Item()
//                    .withString("firstName", personRequest.getFirstName())
//                    .withString("lastName", personRequest.getLastName());
//    }

    private void initDynamoDbClient()
    {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
    }


    // Elasticache




    // Lambda needs NATGateway to access internet - WTF


}
