package com.payhub.mobilemoney.airtel.jpa.entities;

import com.payhub.mobilemoney.airtel.constants.TransactionStatus;
import com.payhub.mobilemoney.airtel.constants.TransactionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "t_transaction", schema = "airtel_money")
public class Transaction {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "payermsisdn", nullable = false)
    private String payerMsisdn;

    @Column(name = "payeemsisdn", nullable = false)
    private String payeeMsisdn;

    @Column(name = "amount")
    private double amount;

    @Column(name = "transactionid", nullable = false, unique = true)
    private String transactionId;

    /*@Column(name = "trid", nullable = false, unique = true)
    private String trId;*/

    @Column(name = "transactionstatus", nullable = false, unique = true)
    private TransactionStatus transactionStatus;

    @Column(name = "createdon", nullable = false)
    private Date createdOn;

    @Column(name = "transactiontype", nullable = false)
    private TransactionType transactionType;

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayerMsisdn() {
        return payerMsisdn;
    }

    public void setPayerMsisdn(String payerMsisdn) {
        this.payerMsisdn = payerMsisdn;
    }

    public String getPayeeMsisdn() {
        return payeeMsisdn;
    }

    public void setPayeeMsisdn(String payeeMsisdn) {
        this.payeeMsisdn = payeeMsisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /*public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }*/

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
