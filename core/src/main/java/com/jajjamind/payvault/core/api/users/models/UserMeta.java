package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.jpa.models.enums.GenderEnum;

import java.util.Date;

/**
 * @author akena
 * 18/01/2021
 * 03:38
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMeta {

    private String firstName;
    private String lastName;
    private String middleName;
    private Long userId;
    private Long agentId;
    private String phoneNumber;
    private String phoneNumber2;
    private String imagePath;
    private String displayName;
    private GenderEnum gender;
    private Date birthDate;
    private String email;
    private Country countryCode;
    private String identification;
    private String identificationNumber;
    private String identificationPath;
    private boolean nonVerifiedEmail;
    private boolean nonVerifiedPhoneNumber;

    public void validate(){
        Validate.notEmpty(firstName,"First name is required");
        Validate.notEmpty(lastName,"Last name is required");
        Validate.notEmpty(phoneNumber,"Phone number is required");
        Validate.notNull(gender,"Gender cannot be null");
        Validate.notNull(countryCode,"Agent's country is required");
    }

}
