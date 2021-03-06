package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.account.models.Account;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;

import java.util.Date;

/**
 * @author akena
 * 18/01/2021
 * 03:17
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;
    private Account productAccount;
    private String name;
    private StatusEnum status;
    private NameAndNoteModel provider;
    private NameAndNoteModel rootProvider;
    private NameAndNoteModel productCategory;
    private String productCode;
    private String officialName;
    private Boolean hasCharge;
    private Boolean hasTariff;
    private Boolean hasSmsNotification;
    @JsonFormat(pattern = DateTimeUtil.YYYY_MM_DD)
    private Date createdOn;
    @JsonFormat(pattern = DateTimeUtil.YYYY_MM_DD)
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;

    public void validate(){
        Validate.notNull(productAccount,"Product account is required");
        Validate.notEmpty(provider.getName(),"Provide a provider name");
        Validate.notNull(productCategory,"Provide a product category");
        Validate.notEmpty(officialName,"Provide an official name for the product");
        Validate.notNull(rootProvider,"Root service provider");
    }
}
