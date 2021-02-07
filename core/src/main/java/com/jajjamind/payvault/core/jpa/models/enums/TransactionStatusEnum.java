package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 13/12/2020
 * 11:42
 **/
public enum TransactionStatusEnum {
    PENDING("Transaction has not been fully processed"),
    SUCCESSFUL("Transaction has been processed by all parties involved"),
    FAILED("Transaction processing has failed");

    private final String description;
    private  TransactionStatusEnum(String description){
        this.description = description;
    }

    public String getDescription(){
        return  this.description;
    }
}
