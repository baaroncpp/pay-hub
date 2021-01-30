package com.jajjamind.payvault.core.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author akena
 * 30/01/2021
 * 03:38
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Approve {
    private String note;
    private Long id;
}
