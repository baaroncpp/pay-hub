package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.jpa.models.agent.TAgentCustomer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author akena
 * 16/12/2020
 * 01:24
 **/
@Entity
@Table(name = "t_transaction",schema = "core")
public class TTransaction {

    private String id;
    private String externalTransactionId;
    private String providerTransactionId;
    private TProduct product;
    private TAgent agent;
    private TAgentCustomer customer;
    private BigDecimal amount;
    private boolean reversed;
    private TransactionStatusEnum statusEnum;
    private String providerStatus;
    private String providerStatusDescription;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "external_transaction_id")
    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    @Column(name = "provider_transaction_id")
    public String getProviderTransactionId() {
        return providerTransactionId;
    }

    public void setProviderTransactionId(String providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
    }

    @JoinColumn(name = "last_transaction_product",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    @JoinColumn(name = "last_transaction_product",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }

    @JoinColumn(name = "last_transaction_product",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgentCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(TAgentCustomer customer) {
        this.customer = customer;
    }


    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    @Column(name = "reversed")
    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TransactionStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(TransactionStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    @Column(name = "provider_status")
    public String getProviderStatus() {
        return providerStatus;
    }

    public void setProviderStatus(String providerStatus) {
        this.providerStatus = providerStatus;
    }

    @Column(name = "provider_status_description")
    public String getProviderStatusDescription() {
        return providerStatusDescription;
    }

    public void setProviderStatusDescription(String providerStatusDescription) {
        this.providerStatusDescription = providerStatusDescription;
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
