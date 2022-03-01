package com.gui.ecommerceDynamoDB.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gui.ecommerceDynamoDB.model.AppUser;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AppUserForm {
	
	@NotNull @NotEmpty
	private String fullName;
	
	@NotNull @NotEmpty
	private String email;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;

	public AppUser transform() {
		return new AppUser(this.fullName, this.email, this.dateOfBirth);
	}

}
