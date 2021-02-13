package com.jajjamind.payvault.core.api.account.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author akena
 * 14/01/2021
 * 21:16
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountingGroup {

    private Long id;
    private String name;
    private AccountTypeEnum groupType;
    private String note;
    private Boolean canBulkLiquidate;
    @JsonFormat( pattern = DateTimeUtil.DD_MM_YYYY)
    private Date createdOn;
    @JsonFormat( pattern = DateTimeUtil.DD_MM_YYYY)
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;

    public void validate(){
        Validate.notEmpty(name,"Name should be provided");
        Validate.notEmpty(note,"Group description is required");
        Validate.notNull(canBulkLiquidate,"Indicate if group can be indicated in one go");
    }
}
