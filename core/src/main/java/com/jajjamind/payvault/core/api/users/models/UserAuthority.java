package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author akena
 * 26/01/2021
 * 12:45
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.Data
public class UserAuthority {

    private Integer id;
    private String username;
    private String authority;

}
