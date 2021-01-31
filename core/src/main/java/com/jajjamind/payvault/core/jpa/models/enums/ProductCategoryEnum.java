package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 13/12/2020
 * 14:12
 **/
public enum ProductCategoryEnum {
    DATA_BUNDLES("DB"),
    AIRTIME ("AT"),
    ELECTRICITY ("EL"),
    WATER ("WT"),
    TV ("TV"),
    LOANS ("LN"),
    MOBILE_MONEY ("MN"),
    COLLECTIONS("CL");

    private final String code;

    ProductCategoryEnum(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }
}
