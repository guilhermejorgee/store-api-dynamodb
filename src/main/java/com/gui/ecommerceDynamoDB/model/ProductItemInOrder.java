package com.gui.ecommerceDynamoDB.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DynamoDBDocument
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductItemInOrder {
	
	@NotNull @NotEmpty
	@DynamoDBAttribute(attributeName = "product_id")
	String productId;
	
	@DynamoDBAttribute
	@NotNull @NotEmpty
	String name;
	
	@DynamoDBAttribute
	@NotNull
	BigDecimal price;

}
