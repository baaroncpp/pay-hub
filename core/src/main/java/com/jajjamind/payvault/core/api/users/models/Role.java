package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 26/01/2021
 * 14:00
 **/

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
    private Integer id;
    private String name;
    private String note;
}
