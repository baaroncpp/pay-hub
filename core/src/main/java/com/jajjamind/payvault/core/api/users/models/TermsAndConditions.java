package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author akena
 * 18/01/2021
 * 03:39
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermsAndConditions {

    private Long id;
    private String versionNumber;
    private String content;
    private Boolean isActive;
    private String target;
    private Date createdOn;
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;

}
