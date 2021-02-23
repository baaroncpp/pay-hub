package com.jajjamind.payvault.core.api.config.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;

import java.util.Date;

/**
 * @author akena
 * 22/02/2021
 * 12:25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@lombok.Data
public class ProviderConfiguration {

    private Long id;
    private String name;
    private String endpoint;
    private String username;
    private String password;
    private String apiKey;
    private Integer connectionPort;
    private Integer readTimeout;
    private Integer connectTimeout;
    private Integer writeTimeout;
    private Date createdOn;
    private Date updatedOn;

    public void validate(){
        Validate.notEmpty(name,"Name cannot be null");
    }
}
