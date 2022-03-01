package com.gui.ecommerceDynamoDB.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gui.ecommerceDynamoDB.model.AppUser;

import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppUserDto {

	protected String userId;
	protected String fullName;	
	protected String email;	
	protected String dateOfBirth;
	protected String createdAt;
	protected String updateAt;
	
	public AppUserDto(AppUser user) {
		this.userId = user.getUserId();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.dateOfBirth = LocalDate.parse(user.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.createdAt = LocalDateTime.parse(user.getCreatedAt()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		this.updateAt = user.getUpdateAt() == null? null : LocalDateTime.parse(user.getUpdateAt()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}


}
