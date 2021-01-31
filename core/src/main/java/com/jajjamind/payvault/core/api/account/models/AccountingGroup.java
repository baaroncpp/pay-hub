package com.jajjamind.payvault.core.api.account.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;

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
    private String note;
    private Boolean canBulkLiquidate;

    public void validate(){
        Validate.notEmpty(name,"Name should be provided");
        Validate.notEmpty(note,"Group description is required");
        Validate.notNull(canBulkLiquidate,"Indicate if group can be indicated in one go");
    }
}
