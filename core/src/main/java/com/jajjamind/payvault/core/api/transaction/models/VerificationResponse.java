package com.jajjamind.payvault.core.api.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 07/02/2021
 * 17:07
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerificationResponse {

    private String accountNumber;
    private String customerName;
    private String accountBalance;
    private String location;

}
