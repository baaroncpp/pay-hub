package com.jajjamind.payvault.core.utils;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;

/**
 * @author akena
 * 20/01/2021
 * 02:50
 **/
public class ValidateUtils {

    private ValidateUtils(){}
    
   public static CountryEnum validateAndGetCountry(String countryCode){
        final  CountryEnum country = CountryEnum.getFromISO2Code(countryCode);
        Validate.notNull(country, ErrorMessageConstants.COUNTRY_PROVIDED_DOES_NOT_FOUND,countryCode);

        return country;
    }
}
