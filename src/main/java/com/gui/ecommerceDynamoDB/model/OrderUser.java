package com.gui.ecommerceDynamoDB.model;

import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "user_ecommerce")
@Data
@NoArgsConstructor
public class OrderUser {

	@DynamoDBHashKey(attributeName = "pk")
	private String partitionKey;
	
	@DynamoDBRangeKey(attributeName = "sk")
	private String sortKey;
	
	@DynamoDBAttribute(attributeName = "order_id")
	private String orderId = UUID.randomUUID().toString();
	
	@DynamoDBAttribute(attributeName = "order_user_id")
	private String orderUserId;
	
	@DynamoDBAttribute
	private List<ProductItemInOrder> items;
	
	public OrderUser(String orderUserId, List<ProductItemInOrder> items) {
		this.items = items;
		this.orderUserId = orderUserId;
	}
	
	public void modifyKeysDbPattern() {
		var keys = IdentificationTagsDB.setTagUserOrder(this.orderUserId, this.orderId);
		this.partitionKey = keys.get(IdentificationTagsDB.ID_USER_TABLE);
		this.sortKey = keys.get(IdentificationTagsDB.ENTITY_ORDER_TABLE);
	}
	
	
}
