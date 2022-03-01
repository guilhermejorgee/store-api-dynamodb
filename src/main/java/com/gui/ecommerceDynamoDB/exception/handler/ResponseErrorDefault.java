package com.gui.ecommerceDynamoDB.exception.handler;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseErrorDefault {
	
	private int status;
	private String field;
	private String message;
	
	public ResponseErrorDefault(int status, String field, String message) {
		this.status = status;
		this.field = field;
		this.message= message;
	}
	
	public ResponseErrorDefault(int status, FieldError obj) {
		this.status = status;
		this.field = obj.getField();
		this.message = obj.getDefaultMessage();
	}

}
