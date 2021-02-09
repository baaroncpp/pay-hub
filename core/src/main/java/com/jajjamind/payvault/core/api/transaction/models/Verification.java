package com.jajjamind.payvault.core.api.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author akena
 * 07/02/2021
 * 17:02
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Verification {

    private String productCode;
    private String accountNumber;
    private String location;

}
