package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;

import javax.persistence.*;

/**
 * @author akena
 * 02/02/2021
 * 01:13
 **/
@Entity
@Table(name = "t_account_mapping",schema = "core")
public class TAccountMapping extends AuditedEntity{

    private Integer bankId;
    private Long agentId;
    private Long agentIdCommission;
    private Integer productId;
    private TAccount accountId;
    private StatusEnum status;
    private Boolean systemAccount;

    @Column(name = "bank_id")
    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @JoinColumn(name = "account_id",referencedColumnName = "id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    public TAccount getAccountId() {
        return accountId;
    }


    public void setAccountId(TAccount accountId) {
        this.accountId = accountId;
    }

    @Column(name = "status")
    public StatusEnum getStatus() {
        return status;
    }

    @Column(name = "agent_id")
    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Column(name = "system_account")
    public Boolean getSystemAccount() {
        return systemAccount;
    }

    public void setSystemAccount(Boolean systemAccount) {
        this.systemAccount = systemAccount;
    }

    @Column(name = "agent_id_commission")
    public Long getAgentIdCommission() {
        return agentIdCommission;
    }

    public void setAgentIdCommission(Long agentIdCommission) {
        this.agentIdCommission = agentIdCommission;
    }
}
