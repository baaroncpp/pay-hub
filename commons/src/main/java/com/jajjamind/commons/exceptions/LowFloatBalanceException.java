package com.jajjamind.commons.exceptions;

public class LowFloatBalanceException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String message;

    public LowFloatBalanceException(String message) {
        super(message);
        this.message = message;
    } 
    
    
}