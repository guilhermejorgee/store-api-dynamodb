package com.gui.ecommerceDynamoDB.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppUserFormUpdate {

	@NotNull @NotEmpty
	private String userId;
	
	private String fullName;
	
	private String email;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
}
