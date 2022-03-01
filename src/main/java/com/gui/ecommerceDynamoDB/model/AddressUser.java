package com.gui.ecommerceDynamoDB.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import lombok.Data;

@DynamoDBDocument
@Data
public class AddressUser {
	
	@DynamoDBAttribute
	private String id = UUID.randomUUID().toString();
	
	@DynamoDBAttribute
	@DynamoDBTypeConvertedEnum
	@NotNull
	private TypeAddress type = TypeAddress.NULL;
	
	@DynamoDBAttribute
	@NotNull @NotEmpty
	private String streetAddress;
	
	@DynamoDBAttribute
	@NotNull
	private Integer postalCode;
	
	@DynamoDBAttribute
	@NotNull @NotEmpty
	private String state;
	
	
	
	
}
