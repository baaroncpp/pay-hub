package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 18/01/2021
 * 03:19
 **/
public enum StatusEnum {
    ACTIVE (Boolean.TRUE),
    NOT_ACTIVE (Boolean.FALSE);

    private final Boolean boolValue;
    StatusEnum(Boolean boolValue){
        this.boolValue = boolValue;
    }

    public Boolean getBoolValue(){
        return boolValue;
    }

    public static StatusEnum getFromBoolean(boolean bool){
        if(bool)
            return StatusEnum.ACTIVE;

        return StatusEnum.NOT_ACTIVE;
    }
}
