package com.jajjamind.payvault.core.api.account.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.utils.Money;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author akena
 * 30/01/2021
 * 03:49
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CashFlow {

    private Long businessUser;
    private Long mainAccountId;
    private Long productAccountId;
    @NotNull(message = "Amount is required")
    private Money amount;
    @NotEmpty(message = "Comment on this request cannot be empty")
    private String note;
    private String initiatorReference;

    public void validate(){
        Validate.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, ErrorMessageConstants.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
    }

}
