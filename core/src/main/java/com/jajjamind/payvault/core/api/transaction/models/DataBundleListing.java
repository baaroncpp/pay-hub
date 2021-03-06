package com.jajjamind.payvault.core.api.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 30/01/2021
 * 03:59
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataBundleListing {

    private String bundleCode;
    private String bundleName;
    private String bundleAmount;
    private String currency;
}
