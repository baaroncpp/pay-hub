package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 13/12/2020
 * 14:59
 **/
public enum TransactionTypeEnum {

    CASH_FLOW_DEBIT, //Money is leaving account
    CASH_FLOW_CREDIT, //Money is coming account
    AGENT_TRANS_DEBIT,
    AGENT_TRANS_CREDIT
}
