package com.gui.ecommerceDynamoDB.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;

@DynamoDBTable(tableName = "product_ecommerce")
@Data
public class ProductItem {
	
	@DynamoDBHashKey(attributeName = "pk")
	private String partitionKey;
	
	@DynamoDBRangeKey(attributeName = "sk")
	private String sortKey;
	
	@DynamoDBRangeKey(attributeName = "product_id")
	String productId = UUID.randomUUID().toString();
	
	@DynamoDBRangeKey()
	String name;
	
	@DynamoDBRangeKey()
	BigDecimal price;
	
	@DynamoDBRangeKey()
	Integer quantity;
	
	

}
