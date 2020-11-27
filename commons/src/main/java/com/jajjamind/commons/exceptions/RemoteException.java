/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jajjamind.commons.exceptions;

/**
 * * @author napho
 */
public class RemoteException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String message;

    public RemoteException(String message,Exception ex) {
        super(message,ex);
        this.message = message;
    } 
    
    
}
