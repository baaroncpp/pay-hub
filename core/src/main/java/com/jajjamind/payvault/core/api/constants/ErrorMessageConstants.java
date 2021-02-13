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
    public static final String ACCOUNT_NOT_ASSIGNED_TO_ANY_ENTITY = "Account with id %s not assigned to any entity";
    public static final String PRODUCT_WITH_ID_NOT_FOUND = "Product with ID %s not found";
    public static final String PRODUCT_REQUEST_FAILED = "Product requested could not be found in the system";
    public static final String PRODUCT_REQUEST_FAILED_NOT_ACTIVE = "Request could not be processed. Product is currently disabled";
    public static final String CURRENCY_CANNOT_BE_NULL = "Currency cannot be empty";
    public static final String AGENT_DETAILS_IS_REQUIRED = "Agent details is required";
    public static final String COUNTRY_PROVIDED_DOES_NOT_FOUND = "Country with code %s could not be found";
    public static final String COMPANY_WITH_ID_COULD_NOT_BE_FOUND = "Company with ID %s could not be found";
    public static final String COUNTRY_PROVIDED_DOES_NOT_MATCH = "Company country %s and agent's country %s do not match";
    public static final String USER_WITH_ID_NOT_FOUND = "User with ID %s not found";
    public static final String ACCOUNT_MAPPING_FOR_BANK_DOES_NOT_EXIST = "Mapping for account %s and bank %s does not exist";
    public static final String ACCOUNT_BALANCE_MUST_BE_ZERO = "Account balance must be zero before its assigned or unassigned";
    public static final String ACCOUNT_IS_CLOSED = "Account is already closed and cannot be operated";
    public static final String AGENT_WITH_ID_NOT_FOUND = "Agent with ID %s could not be found";
    public static final String MAIN_ACCOUNT_ID_MISSING = "Main account ID is missing";
    public static final String AMOUNT_MUST_BE_GREATER_THAN_ZERO = "Amount to transact should be greater than 0";
    public static final String INSUFFICIENT_FUNDS_ON_ACCOUNT = "Insufficient balance on account for transaction";
    public static final String NO_PENDING_APPROVAL_FOUND = "There is no request pending approval";
    public static final String APPROVAL_STATUS_UNKNOWN = "Unknown approval option received";
    public static final String PRODUCT_COMMISSION_PRODUCT_REQUIRED = "A product is required for the commission being created";
    public static final String NO_PRODUCT_ACCESS = "You do not have access to this product. Please contact support";
    public static final String COMMISSION_ACCOUNT_REQUIRED = "Account provided must be a commission account";
    public static final String AGENT_ACCOUNT_NOT_FOUND = "Agent account could not be found in the system. Please contact support";

    public static final String OPERATION_NOT_SUPPORTED = "The operation requested is currently not supported by system. Please contact support";

}
