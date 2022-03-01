package com.gui.ecommerceDynamoDB.exception.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.gui.ecommerceDynamoDB.exception.OrderUserNotFoundException;
import com.gui.ecommerceDynamoDB.exception.UsernameNotFoundException;
import com.gui.ecommerceDynamoDB.util.IdentificationTagsDB;

@RestControllerAdvice
public class ExceptionHandlingController {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ResponseErrorDefault> usernameNotFound(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorDefault(HttpStatus.NOT_FOUND.value(), IdentificationTagsDB.ID_USER_FIELD, ex.getMessage()));
	}
	
	@ExceptionHandler(OrderUserNotFoundException.class)
	public ResponseEntity<ResponseErrorDefault> orderUserNotFound(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorDefault(HttpStatus.NOT_FOUND.value(), IdentificationTagsDB.ID_ORDER_FIELD, ex.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ResponseErrorDefault>> argumentNotValid(MethodArgumentNotValidException ex){
		List<ResponseErrorDefault> listaErros = ex.getFieldErrors().stream().map(e -> new ResponseErrorDefault(HttpStatus.BAD_REQUEST.value(), e)).toList();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErros);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<ResponseErrorDefault> amazonException(AmazonServiceException ex){
		return ResponseEntity.status(ex.getStatusCode()).body(new ResponseErrorDefault(ex.getStatusCode(), ex.getErrorType().toString(), ex.getErrorMessage()));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseErrorDefault> resourceAmazonNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(ex.getStatusCode()).body(new ResponseErrorDefault(ex.getStatusCode(), ex.getErrorType().toString(), ex.getErrorMessage()));
	}
	
}
