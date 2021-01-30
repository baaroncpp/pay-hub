package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 13/12/2020
 * 13:40
 **/
public enum AccountTypeEnum {

    MAIN ("MN"),
    COLLECTION("CL"),
    BULK_PAYMENT ("BP"),
    COMMISSION ("CM"),
    PAYOUT("PO");

    private final String acronym;

    AccountTypeEnum(String acronym){
        this.acronym = acronym;
    }

    public String getAcronym(){
        return this.acronym;
    }
}
