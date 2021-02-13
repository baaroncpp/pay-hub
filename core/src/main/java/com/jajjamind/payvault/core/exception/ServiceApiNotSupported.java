package com.jajjamind.payvault.core.exception;

/**
 * @author akena
 * 12/02/2021
 * 23:58
 **/
public class ServiceApiNotSupported extends  UnsupportedOperationException {

    final String message;
    public ServiceApiNotSupported(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
