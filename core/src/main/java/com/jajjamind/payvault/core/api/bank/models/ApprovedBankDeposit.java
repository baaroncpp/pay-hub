package com.jajjamind.payvault.core.api.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.payvault.core.api.account.models.Account;

/**
 * @author akena
 * 02/02/2021
 * 01:09
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApprovedBankDeposit {

    private BankDeposit bankDeposit;
    private Account account;
    private String approver1;
    private String approverNote1;
    private String approver2;
    private String approverNote2;
    private String status;

}
