package com.jajjamind.commons.exceptions;

/**
 * @author akena
 * 13/02/2021
 * 01:08
 **/
public class BadRequestException extends RuntimeException{

    final String message;

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

    public BadRequestException(String message,Object ... messageConstants){
        this.message = String.format(message,messageConstants);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
