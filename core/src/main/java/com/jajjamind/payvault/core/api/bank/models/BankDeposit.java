package com.jajjamind.payvault.core.api.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author akena
 * 30/01/2021
 * 03:31
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDeposit {

    private String id;
    private Long agentId;
    private BigDecimal amountDeposited;
    private TransactionStatusEnum status;
    private String bankReference;
    private Long bankId;
    private String payslipImagePath;
    private Date payslipTimestamp;

}
