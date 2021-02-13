package com.jajjamind.payvault.core.api.account.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author akena
 * 14/01/2021
 * 21:13
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class Account {

    private Long id;
    private String name;
    private String code;
    private AccountTypeEnum accountType;
    private AccountingGroup accountGrouping;
    private BigDecimal balanceToNotifyAt;
    @JsonFormat( pattern = DateTimeUtil.YYYY_MM_DD_HH_MM_SS)
    private Date balanceNotificationSentOn;
    private BigDecimal availableBalance;
    private AccountStatusEnum accountStatus;
    private String statusDescription;
    @JsonFormat( pattern = DateTimeUtil.DD_MM_YYYY)
    private Date activatedOn;
    private String activatedBy;
    private Date suspendedOn;
    private String suspendedBy;
    @JsonFormat( pattern = DateTimeUtil.DD_MM_YYYY)
    private Date closedOn;
    private String closedBy;
    private String createdBy;
    private String modifiedBy;
    @JsonFormat( pattern = DateTimeUtil.DD_MM_YYYY)
    private Date createdOn;
    private Date modifiedOn;

    public void validate(){
        Validate.notEmpty(name,"Account name is required");
        Validate.notNull(accountType,"Account type is required");

    }
}
