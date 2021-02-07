package com.jajjamind.payvault.core.api.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
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
    private BigDecimal amountDeposited;
    private TransactionStatusEnum status;
    private String bankReference;
    private Bank bank;
    private String payslipImagePath;
    private Date payslipTimestamp;
    private String externalDepositorName;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;

    public void validate(){

        Validate.isTrue(amountDeposited.compareTo(BigDecimal.ZERO) > 0,"Amount should be greater than zero");
        Validate.notNull(bankReference, "A bank transaction ID reference is required");
        Validate.notNull(bank,"Bank for deposit is required");
        Validate.notNull(payslipTimestamp,"Payslip timestamp cannot be null");
        Validate.notEmpty(externalDepositorName,"Depositor is required");
        Date date = DateTimeUtil.getCurrentUTCTime();
        Validate.isTrue(payslipTimestamp.before(date),"Payslip cannot be created before record entry");
    }

}
