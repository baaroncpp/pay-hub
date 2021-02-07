package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date createdOn;
    private Date modifiedOn;


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
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "modified_on")
    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

 //<editor-fold>
    public static class Builder {

        private String id;
        private TAccount account;
        private TransactionTypeEnum transactionType;
        private Boolean nonReversal;
        private TransactionStatusEnum transactionStatus;
        private String statusDescription;
        private BigDecimal balanceBefore;
        private BigDecimal balanceAfter;
        private String externalTransactionId;
        private Date createdOn;
        private Date modifiedOn;


        public Builder(TAccount account){
            this.account = account;
        }

        public Builder withId(String id){
            this.id = id;
            return this;

        }

        public Builder withTransactionType(TransactionTypeEnum transactionType){
            this.transactionType = transactionType;
            return this;
        }

        public Builder withNonReversal(Boolean nonReversal){
            this.nonReversal = nonReversal;
            return this;
        }

        public Builder withTransactionStatus(TransactionStatusEnum transactionStatus){
            this.transactionStatus = transactionStatus;
            this.statusDescription = transactionStatus.getDescription();
            return this;
        }

        public Builder withBalanceBefore(BigDecimal balanceBefore){
            this.balanceBefore = balanceBefore;
            return this;
        }

        public Builder withBalanceAfter(BigDecimal balanceAfter){
            this.balanceAfter = balanceAfter;
            return this;
        }

        public Builder withExternalTransactionId(String externalTransactionId){
            this.externalTransactionId = externalTransactionId;
            return this;
        }

        public Builder withCreatedOn(Date createdOn){
            this.createdOn = createdOn;
            return this;
        }

        public Builder withModifiedOn(Date modifiedOn){
            this.modifiedOn = modifiedOn;
            return this;
        }

        public TAccountTransaction build(){
            final TAccountTransaction t = new TAccountTransaction();
            t.setTransactionStatus(this.transactionStatus);
            t.setTransactionType(this.transactionType);
            t.setNonReversal(this.nonReversal);
            t.setBalanceAfter(this.balanceAfter);
            t.setBalanceBefore(this.balanceBefore);
            t.setCreatedOn(this.createdOn);
            t.setId(this.id);
            t.setModifiedOn(this.modifiedOn);
            t.setStatusDescription(this.statusDescription);
            t.setAccount(this.account);
            t.setExternalTransactionId(this.externalTransactionId);

            return t;

        }

    }
    //</editor-fold>
}
