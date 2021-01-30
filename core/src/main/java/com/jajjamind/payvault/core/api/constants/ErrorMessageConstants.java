package com.jajjamind.payvault.core.api.constants;

/**
 * @author akena
 * 12/01/2021
 * 12:53
 **/
public class ErrorMessageConstants {

    private ErrorMessageConstants(){}

    public static final String BANK_ACCOUNT_NOT_FOUND = "Bank account with id %s not found";
    public static final String BANK_PAYLOAD_CANNOT_BE_NULL = "Bank payload cannot be null";
    public static final String TARIFF_EXISTS_IN_SYSTEM = "Tariff range already exists in system";
    public static final String CHARGE_WITH_ID_NOT_FOUND = "Charge with ID %s could not be found";
    public static final String ACCOUNT_ID_CANNOT_BE_NULL = "Account ID cannot be null";
    public static final String ACCOUNT_WITH_ID_NOT_FOUND = "Account with ID %s not found";
    public static final String ACCOUNT_ALREADY_ASSIGNED = "Account with ID %s is already assigned to another product";
    public static final String PRODUCT_WITH_ID_NOT_FOUND = "Product with ID %s not found";
    public static final String CURRENCY_CANNOT_BE_NULL = "Currency cannot be empty";
    public static final String AGENT_DETAILS_IS_REQUIRED = "Agent details is required";
    public static final String COUNTRY_PROVIDED_DOES_NOT_FOUND = "Country with code %s could not be found";
    public static final String COMPANY_WITH_ID_COULD_NOT_BE_FOUND = "Company with ID %s could not be found";
    public static final String COUNTRY_PROVIDED_DOES_NOT_MATCH = "Company country %s and agent's country %s do not match";

}
