package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.enums.CurrencyEnum;
import lombok.EqualsAndHashCode;


/**
 * @author akena
 * 18/01/2021
 * 23:14
 **/
@lombok.Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCommissionTemplate extends BasePricing{

    private CurrencyEnum currency;


    @Override
    public void validate() {
        super.validate();
        Validate.notNull(currency, ErrorMessageConstants.CURRENCY_CANNOT_BE_NULL);
    }
}
