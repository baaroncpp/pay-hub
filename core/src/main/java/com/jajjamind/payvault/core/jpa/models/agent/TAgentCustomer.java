package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.product.TProduct;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author akena
 * 16/12/2020
 * 00:50
 **/
@Entity
@Table(name = "t_agent_customer",schema = "core")
public class TAgentCustomer {

    private String id;
    private String accountId;
    private TAgent agent;
    private String customerName;
    private LocalDateTime lastTransactedOn;
    private LocalDateTime lastTransactionAmount;
    private TProduct lastTransactionProduct;
    private long totalTransactions;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JoinColumn(name = "agent_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }

    @Column(name = "account_id")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "last_transacted_on")
    public LocalDateTime getLastTransactedOn() {
        return lastTransactedOn;
    }

    public void setLastTransactedOn(LocalDateTime lastTransactedOn) {
        this.lastTransactedOn = lastTransactedOn;
    }

    @Column(name = "last_transaction_amount")
    public LocalDateTime getLastTransactionAmount() {
        return lastTransactionAmount;
    }

    public void setLastTransactionAmount(LocalDateTime lastTransactionAmount) {
        this.lastTransactionAmount = lastTransactionAmount;
    }

    @JoinColumn(name = "last_transaction_product",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TProduct getLastTransactionProduct() {
        return lastTransactionProduct;
    }

    public void setLastTransactionProduct(TProduct lastTransactionProduct) {
        this.lastTransactionProduct = lastTransactionProduct;
    }

    @Column(name = "total_transactions")
    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    @Column(name = "created_on")
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
