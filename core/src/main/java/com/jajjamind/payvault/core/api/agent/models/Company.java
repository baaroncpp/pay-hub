package com.jajjamind.payvault.core.api.agent.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.enums.IdentificationEnum;

/**
 * @author akena
 * 18/01/2021
 * 03:36
 **/
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    private Long id;
    private String businessName;
    private String natureOfBusiness;
    private String physicalAddress;
    private String phoneNumber;
    private District district;
    private String tinNumber;
    private Country registrationCountry;
    private String contactPerson;
    private IdentificationEnum contactIdentification;
    private String contactIdentificationNumber;
    private String contactIdentificationPath;
    private String contactPhoneNumber;
    private String email;
    private String formSerial;
    private String note;

    public void validate(){
        Validate.notEmpty(businessName,"Business name is required");
        Validate.notEmpty(natureOfBusiness, "Nature of Business is required");
        Validate.notEmpty(physicalAddress, "Physical address of company is required");
        Validate.notNull(district, "District in which company is operating is required");
        Validate.notEmpty(tinNumber,"Company tin number is required");
        Validate.notNull(registrationCountry,"Provide a country for which company is registered");
        Validate.notEmpty(contactPerson,"At least one director should be indicated as contact person");
        Validate.notEmpty(contactPhoneNumber,"Provide a contact phone number for the contact person");
        Validate.notEmpty(formSerial,"Company form serial is required");
        Validate.notEmpty(registrationCountry.getIsoAlpha2(),"Country Code is required");
    }
}
