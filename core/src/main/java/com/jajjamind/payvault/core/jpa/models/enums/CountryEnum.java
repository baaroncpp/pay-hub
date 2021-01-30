package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 12/01/2021
 * 13:19
 **/
public enum CountryEnum {

    UGANDA ("UG","UGA",256),
    KENYA ("KE","KEN",254),
    TANZANIA ("TZ","TZA",255),
    RWANDA ("RW","RWA",250);

    private String iso2Code;
    private String iso3Code;
    private int dialingCode;

    CountryEnum(String iso2,String iso3,int dialingCode){
        this.iso2Code = iso2;
        this.iso3Code = iso3;
        this.dialingCode = dialingCode;
    }


    public int getDialingCode() {
        return this.dialingCode;
    }

    public String getIso2Code() {
        return this.iso2Code;
    }

    public String getIso3Code() {
        return this.iso3Code;
    }

    public static CountryEnum getFromISO2Code(String code) {
        for(CountryEnum v : values()){
            if(v.iso2Code.equals(code)) {
                return v;
            }
        }

        return null;
    }
}
