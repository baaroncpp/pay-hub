package com.jajjamind.payvault.core.api.bank.models;

import com.fasterxml.jackson.annotation.*;
import com.jajjamind.payvault.core.api.agent.models.Country;

import java.util.Date;

/**
 * @author akena
 * 11/01/2021
 * 15:31
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bank {

    Integer id;
    private String bankName;
    private String accountName;
    private String accountNumber;
    private String branch;
    private String swiftCode;
    private String currency;
    private Country country;
    private Date createdOn;
    private Date modifiedOn;

}
