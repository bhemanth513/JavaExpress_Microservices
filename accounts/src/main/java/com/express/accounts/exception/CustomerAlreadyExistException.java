package com.express.accounts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAlreadyExistException extends RuntimeException{


	public final Logger logger = LoggerFactory.getLogger(CustomerAlreadyExistException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistException(String msg) {
		super(msg);
	}
}
