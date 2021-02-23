package com.jajjamind.payvault.core.service.utilities;

/**
 * @author akena
 * 21/02/2021
 * 14:40
 **/
public class Sms {

    public static final String REPLACEMENT_REGEX = "##[a-zA-Z]*##";
    public static final String MASK = "XXXX";

    public enum Category {
        OTP, NOTIFICATION
    }

    public enum Provider {
        SKY_API
    }

    public enum Name{
        AGENT_REGISTERED("Sent when agent has been successfully set up in the system for first time"),
        ACCOUNT_LOCKED("Sent after account is locked due to wrong passcode attempts"),
        TRANSACTION_SUCCESSFUL("Sent when a transaction is performed successfully"),
        PAYMENT("Sent after a payout has been successfully made on behalf of a agent"),
        TRANSACTION_TIMEOUT ("Sent after transaction has failed with timeout"),
        ACCOUNT_UNLOCK("Sent after performing accounting unlocking from a previously locked account"),
        PIN_RESET("Pin reset with temporary pin that user is required to change"),
        FLOAT_ALLOCATION("Float allocation to agent has been successfully done"),
        LOW_BALANCE("Indicates a low balance on the agent account"),
        COMMISSION_PAYMENT("Sent on successful payment of commission to agents");

        final String description;

        Name(String description){
            this.description = description;
        }
    }
}
