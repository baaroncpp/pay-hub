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
 * 03:33
 **/
@lombok.Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCommission extends  BasePricing{

    private Product product;
    private CurrencyEnum currency;

    @Override
    public void validate() {
        super.validate();
        Validate.notNull(currency, ErrorMessageConstants.CURRENCY_CANNOT_BE_NULL);
        Validate.notNull(product,ErrorMessageConstants.PRODUCT_COMMISSION_PRODUCT_REQUIRED);
        Validate.notNull(product.getId(),ErrorMessageConstants.PRODUCT_COMMISSION_PRODUCT_REQUIRED);
    }
}
