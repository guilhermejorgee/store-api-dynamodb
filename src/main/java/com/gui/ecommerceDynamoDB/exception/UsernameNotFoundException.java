package com.gui.ecommerceDynamoDB.exception;

public class UsernameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1750091708214104507L;
	
	public UsernameNotFoundException(String message){
		super(message);
	}

}
