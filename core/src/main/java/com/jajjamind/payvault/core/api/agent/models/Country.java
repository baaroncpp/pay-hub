package com.jajjamind.payvault.core.api.agent.models;

/**
 * @author akena
 * 13/01/2021
 * 00:34
 **/
@lombok.Data
public class Country {
    private String name;
    private String isoAlpha2;
    private String isoAlpha3;
    private Integer countryCode;
}
