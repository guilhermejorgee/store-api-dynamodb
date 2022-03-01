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
import com.gui.ecommerceDynamoDB.model.AppUser;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

@Repository
public class AppUserRepository {
	
	DynamoDBMapper mapper;
	
	public AppUserRepository(AmazonDynamoDB clientDynamoDb) {
		mapper = new DynamoDBMapper(clientDynamoDb);
	}

	public AppUser saveUserDb(AppUser user) {
		mapper.save(user, DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES.config());
		return user;
	}
	
	public Optional<AppUser> getUserDb(String hash, String sort) {
		return Optional.ofNullable(mapper.load(AppUser.class, hash, sort));
	}

	public List<AppUser> getUsersDb() {	
		Map<String, AttributeValue> attributes = new HashMap<>();
		attributes.put(":val1", new AttributeValue().withS(IdentificationTagsDB.ENTITY_USER_TABLE));
		attributes.put(":val2", new AttributeValue().withS(IdentificationTagsDB.ID_USER_TABLE));
		
		DynamoDBQueryExpression<AppUser> query = new DynamoDBQueryExpression<AppUser>()
				.withKeyConditionExpression("sk = :val1 and begins_with(pk, :val2)")
				.withExpressionAttributeValues(attributes)
				.withIndexName(IdentificationTagsDB.GSI_INVERSE)
				.withConsistentRead(false);
			return mapper.query(AppUser.class, query);
	}
	
	

}
