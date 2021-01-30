package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author akena
 * 13/12/2020
 * 14:59
 **/
@Entity
@Table(name = "t_account_transaction",schema = "core")
public class TAccountTransaction {

    private String id;
    private TAccount account;
    private TransactionTypeEnum transactionType;
    private Boolean nonReversal;
    private TransactionStatusEnum transactionStatus;
    private String statusDescription;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private String externalTransactionId;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JoinColumn(name = "account_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccount getAccount() {
        return account;
    }

    public void setAccount(TAccount account) {
        this.account = account;
    }

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "non_reversal")
    public Boolean getNonReversal() {
        return nonReversal;
    }

    public void setNonReversal(Boolean nonReversal) {
        this.nonReversal = nonReversal;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TransactionStatusEnum getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatusEnum transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Column(name = "status_description")
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Column(name = "balance_before")
    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    @Column(name = "balance_after")
    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    @Column(name = "external_transaction_id")
    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    @Column(name = "created_on",insertable = false,updatable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "modified_on")
    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
