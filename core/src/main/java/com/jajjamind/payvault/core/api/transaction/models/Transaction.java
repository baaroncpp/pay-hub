package com.jajjamind.payvault.core.api.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author akena
 * 30/01/2021
 * 03:57
 **/

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @NotEmpty(message = "Product code is missing")
    private String productCode; // Should be identical for identical products e.g MTN airtime
    @NotNull
    private Long agentId;
    @NotEmpty(message = "Account number is required")
    private String accountNumber;
    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    @NotEmpty(message = "Transaction note is required")
    private String note;
    @NotEmpty(message = "Provide login pin to confirm transaction")
    private String pinConfirmation;
    private String countryCode;

}
