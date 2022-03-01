package com.gui.ecommerceDynamoDB.exception;

public class OrderUserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7943832062274103635L;
	
	public OrderUserNotFoundException(String message) {
		super(message);
	}

}
