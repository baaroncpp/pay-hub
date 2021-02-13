package com.jajjamind.payvault.core.service.product;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.product.models.BasePricing;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;
import com.jajjamind.payvault.core.jpa.models.product.TBasePricing;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/**
 * @author akena
 * 19/01/2021
 * 02:04
 **/
public interface BasePricingService<T extends TBasePricing> {

    default void verifyThatTariffWithinRange(BasePricing charge,List<T> tariff){
        if(charge.getPricingType().equals(PricingTypeEnum.TARIFF) && charge.getTariffGroupIdentifier() != null){

            tariff.stream().forEach(t -> {
                //Check if there is a from value within this limit
                if(((t.getFromAmount().compareTo(charge.getFromAmount()) >= 0 && t.getFromAmount().compareTo(charge.getToAmount()) <= 0)
                        || (t.getToAmount().compareTo(charge.getFromAmount()) >= 0 && t.getToAmount().compareTo(charge.getToAmount()) <= 0)) && !t.getId().equals(charge.getId())){
                    throw new BadRequestException(ErrorMessageConstants.TARIFF_EXISTS_IN_SYSTEM);
                }

            });
        }
    }
}
