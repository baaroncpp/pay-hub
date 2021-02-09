package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 13/12/2020
 * 13:53
 **/
public enum AccountStatusEnum {
    ACTIVE("Account is assigned to an agent or product already"),
    NOT_ACTIVE("Account is not assigned to any party and can be used"),
    CLOSED("Account closed for ever and cannot be used"),
    SUSPENDED("Account is temporarily suspended but not closed");

    private final String description;

    AccountStatusEnum(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
