package com.gui.ecommerceDynamoDB.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.gui.ecommerceDynamoDB.form.AppUserFormUpdate;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

import lombok.Data;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "user_ecommerce")
@Data
@NoArgsConstructor
public class AppUser {
	
	@DynamoDBIndexRangeKey(globalSecondaryIndexName = IdentificationTagsDB.GSI_INVERSE, attributeName = "pk")
	@DynamoDBHashKey(attributeName = "pk")
	private String partitionKey;
	
	@DynamoDBIndexHashKey(globalSecondaryIndexName = IdentificationTagsDB.GSI_INVERSE, attributeName = "sk")
	@DynamoDBRangeKey(attributeName = "sk")
	private String sortKey;
	
	@DynamoDBAttribute(attributeName = "user_id")
	private String userId = UUID.randomUUID().toString();
	
	@DynamoDBAttribute(attributeName = "full_name")
	private String fullName;
	
	@DynamoDBAttribute
	private String email;
	
	@DynamoDBAttribute(attributeName = "date_of_birth")
	private String dateOfBirth;
	
	@DynamoDBAttribute(attributeName = "created_at")
	private String createdAt = LocalDateTime.now().toString();
	
	@DynamoDBAttribute(attributeName = "update_at")
	private String updateAt;
	
	@Getter(AccessLevel.NONE)
	@DynamoDBAttribute
	private List<AddressUser> address = new ArrayList<>();
	
	
	public AppUser(String fullName, String email, LocalDate dateOfBirth) {
		this.fullName = fullName;
		this.email = email; 
		this.dateOfBirth = dateOfBirth.toString();
	}
	
	public void addAddress(AddressUser address) {
		this.address.add(address);
	}
	
	public List<AddressUser> getAddress(){
		return Collections.unmodifiableList(this.address);
	}

	public void update(AppUserFormUpdate form) {
		//this.partitionKey = form.getPartitionKey() == null? this.partitionKey : form.getPartitionKey();
		this.fullName = form.getFullName();
		this.email = form.getEmail();
		this.dateOfBirth = form.getDateOfBirth() == null? null : form.getDateOfBirth().toString();
		this.updateAt = LocalDateTime.now().toString();
	}
	
	public void modifyKeysDbPattern() {
		var keys = IdentificationTagsDB.setTagsUserProfile(this.userId);
		this.partitionKey = keys.get(IdentificationTagsDB.ID_USER_TABLE);
		this.sortKey = keys.get(IdentificationTagsDB.ENTITY_USER_TABLE);
	}
	
	
}
