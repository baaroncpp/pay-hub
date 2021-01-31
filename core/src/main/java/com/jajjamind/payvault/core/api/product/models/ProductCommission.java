package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Agent;
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

    private Agent agent;
    private CurrencyEnum currency;

    @Override
    public void validate() {
        super.validate();
        Validate.notNull(currency, ErrorMessageConstants.CURRENCY_CANNOT_BE_NULL);
        Validate.notNull(agent,ErrorMessageConstants.AGENT_DETAILS_IS_REQUIRED);
        Validate.notNull(agent.getId(),ErrorMessageConstants.AGENT_DETAILS_IS_REQUIRED);
    }
}
