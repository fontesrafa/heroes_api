package com.digitalinnovationone.heroesapi.config;

import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;
import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.REGION_DYNAMO;

import java.util.Arrays;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import org.springframework.stereotype.Component;

@Component
public class HeroesTable {
    public static void main (String[] args) throws Exception {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
            .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Heroes";

        try {
            System.out.println("Criando tabela..");
            Table table = dynamoDB.createTable(tableName, 
                            Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                            Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                            new ProvisionedThroughput(5L, 5L));
            table.waitForActive();
            System.out.println("Sucesso " + table.getDescription().getTableStatus());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
