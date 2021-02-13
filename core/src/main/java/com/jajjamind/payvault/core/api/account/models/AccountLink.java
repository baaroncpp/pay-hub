package com.jajjamind.payvault.core.api.account.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 12/02/2021
 * 03:30
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class AccountLink {
    private Long accountId;
    private Long entityId;
    private LinkType linkType;


    public enum LinkType{
        AGENT,
        SYSTEM,

    }
}
