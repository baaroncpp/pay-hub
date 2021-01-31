package com.payhub.mobilemoney.mtn.jpa.entities;


import com.payhub.mobilemoney.mtn.constants.Currency;
import com.payhub.mobilemoney.mtn.constants.Status;
import com.payhub.mobilemoney.mtn.mtnModels.Transactiontype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_transaction", schema = "mtn_mobile_money")
public class Transaction {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "accountholdermsisdn", nullable = false)
    private String accountHolderMsisdn;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "message")
    private String message;

    @Column(name = "mtnfinancialtransactionid")
    private String financialTransactionId;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "createdon", nullable = false)
    private Date createdOn;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "approvalid")
    private String approvalId;

    @Column(name = "transactiontype")
    private Transactiontype transactiontype;

    public Transaction() {
    }

    public Transactiontype getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(Transactiontype transactiontype) {
        this.transactiontype = transactiontype;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountHolderMsisdn() {
        return accountHolderMsisdn;
    }

    public void setAccountHolderMsisdn(String accountHolderMsisdn) {
        this.accountHolderMsisdn = accountHolderMsisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFinancialTransactionId() {
        return financialTransactionId;
    }

    public void setFinancialTransactionId(String financialTransactionId) {
        this.financialTransactionId = financialTransactionId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
