package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author akena
 * 19/01/2021
 * 00:38
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract  class BasePricing {
    private Long id;
    private String name;
    private BigDecimal amount;
    private BigDecimal systemAmount;
    private Float chargePercent;
    private Float systemChargePercent;
    private PricingTypeEnum pricingType;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private BigDecimal tariff;
    private BigDecimal systemTariff;
    private String tariffGroupIdentifier;
    private String note;
    private StatusEnum status;
    private String createdBy;
    private String modifiedBy;
    @JsonFormat(pattern = DateTimeUtil.YYYY_MM_DD)
    private Date createdOn;
    @JsonFormat(pattern = DateTimeUtil.YYYY_MM_DD)
    private Date modifiedOn;

    public void validate(){
        Validate.notEmpty(name,"Name is required");
        Validate.notNull(pricingType,"Price type is required to process");

        if(pricingType.equals(PricingTypeEnum.TARIFF)){
            Validate.notNull(toAmount,"Starting amount is required");
            Validate.notNull(fromAmount,"Final amount is required");
            Validate.isTrue(toAmount.compareTo(fromAmount) > 0,"Final amount must be greater than starting amount");
            Validate.notEmpty(tariffGroupIdentifier,"A group name for the tariffs is required");
        }

        if(pricingType.equals(PricingTypeEnum.FLAT_CHARGE)){
            Validate.notNull(amount,"Charge amount is required for flat charges");
        }

        if(pricingType.equals(PricingTypeEnum.PERCENTAGE)){
            Validate.notNull(chargePercent,"Charge percent is required");
        }
    }
}
