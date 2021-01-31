package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 16/12/2020
 * 00:39
 **/
public enum CurrencyEnum {
    UGX("UG","Uganda Shillings"),
    KSH("KE","Kenya Shillings");

    private final String countryCode;
    private final String description;

    CurrencyEnum(String countryCode,String description){
        this.countryCode = countryCode;
        this.description = description;

    }


    public CurrencyEnum getCurrencyByCountryCode(String countryCode){
        for(CurrencyEnum v : values()){
            if(v.countryCode.equalsIgnoreCase(countryCode)){
                return v;
            }
        }
        return null;
    }
}
