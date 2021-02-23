package com.jajjamind.payvault.core.api.constants;

public enum SmsMessages {

    COMMISSION_PAYMENT("Commission Payment is successful for PayHub. Amount :  %s"),
    INSUFFICIENT_BALANCE("Your transaction TXN Id : %s ,cannot be completed due to insufficient balance. Thank you."),
    FLOAT_ALLOCATION("Allocation to your MSISDN %s of amount:UGX %s is successful. Your balance is UGX %s.Txn Id 10031857618. Tax UGX %s"),
    PIN_RESET("Your PIN has been reset. NEW PIN %s valid for 48hrs"),
    UNLOCKING_ACCOUNT("Your account has been successfully unlocked. Thank you for using PayHub Money"),
    TRANSACTION_TIMEOUT("TransactionId : %s cash amount of UGX %s expired."),
    TV_PAYMENT("Paid UGX %s to %s for Customer: %s successfully. Balance: UGX %s Trans ID:%s Txn Date: %s"),
    LOAD_AIRTIME("Top up of UGX %s for %s is successful. Balance: UGX %s Trans ID:%s Txn Date: %s"),
    ACCOUNT_LOCK("Transaction Failed with TXN Id : %s, Exceeded maximum tries (5 times). The account has been locked."),
    ACCOUNT_CREATION("Account creation is successful for MSISDN %s, PIN %s. Please modify your PIN using short code option. Thank you.");

    String message;

    SmsMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}