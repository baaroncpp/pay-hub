package com.jajjamind.payvault.core.jpa.models.enums;

/**
 * @author akena
 * 04/02/2021
 * 05:22
 **/
public enum CashFlowEnum {

    MAIN_TO_BUSINESS("Money is leaving bank account and going to agent or business"),
    PROVIDER_LIQUIDATION("Money is leaving account product (collection account) and being given to product service provider eg cellulant (main account)"),
    STOCK_WITHDRAW("Money is leaving the system entirely"),
    BUSINESS_TO_MAIN("Money is leaving the an agents account and going to main account (bank account)");

    private final String description;
    private CashFlowEnum(String description){
        this.description = description;
    }

}
