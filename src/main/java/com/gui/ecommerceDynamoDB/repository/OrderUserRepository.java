package com.gui.ecommerceDynamoDB.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.gui.ecommerceDynamoDB.model.OrderUser;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

@Repository
public class OrderUserRepository {
	
	DynamoDBMapper mapper;
	
	public OrderUserRepository(AmazonDynamoDB clientDynamoDb) {
		mapper = new DynamoDBMapper(clientDynamoDb);
	}

	public OrderUser saveOrderDb(OrderUser order) {
		mapper.save(order, DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES.config());
		return order;
	}

	public Optional<OrderUser> getOrderDb(String id) {
		
		Map<String, AttributeValue> attributes = new HashMap<>();
		attributes.put(":val1", new AttributeValue().withS(IdentificationTagsDB.ENTITY_ORDER_TABLE + id)); 
		attributes.put(":val2", new AttributeValue().withS(IdentificationTagsDB.ID_USER_TABLE));
		
		DynamoDBQueryExpression<OrderUser> query = new DynamoDBQueryExpression<OrderUser>()
				.withKeyConditionExpression("sk = :val1 and begins_with(pk,:val2)")
				.withExpressionAttributeValues(attributes)
				.withIndexName(IdentificationTagsDB.GSI_INVERSE)
				.withConsistentRead(false);
		
		return Optional.ofNullable(mapper.query(OrderUser.class, query).stream().findFirst().orElse(null));
	}

	public List<OrderUser> getOrdersUserDb(String idUser) {
		
		Map<String, AttributeValue> attributes = new HashMap<>();
		
		attributes.put(":val1", new AttributeValue().withS(IdentificationTagsDB.ID_USER_TABLE + idUser));
		attributes.put(":val2", new AttributeValue().withS(IdentificationTagsDB.ENTITY_ORDER_TABLE));
		
		DynamoDBQueryExpression<OrderUser> query = new DynamoDBQueryExpression<OrderUser>()
				.withKeyConditionExpression("pk = :val1 and begins_with(sk,:val2)")
				.withExpressionAttributeValues(attributes)
				.withConsistentRead(false);
		
		return mapper.query(OrderUser.class, query);
		
	}
	
	
	
	
	

	

}
