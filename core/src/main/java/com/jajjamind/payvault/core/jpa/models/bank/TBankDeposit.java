package com.jajjamind.payvault.core.jpa.models.bank;

import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author akena
 * 16/12/2020
 * 00:35
 **/
@Entity
@Table(name = "t_bank_deposits",schema = "core")
public class TBankDeposit {

    private String id;
    private TAgent agent;
    private BigDecimal amountDeposited;
    private TransactionStatusEnum status;
    private String bankReference;
    private TBankAccount bank;
    private String payslipImagePath;
    private LocalDateTime payslipTimestamp;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private TUser createdBy;
    private TUser modifiedBy;


    @Id
    @Column(name = "id")
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

    @Column(name = "deposit_amount")
    public BigDecimal getAmountDeposited() {
        return amountDeposited;
    }

    public void setAmountDeposited(BigDecimal amountDeposited) {
        this.amountDeposited = amountDeposited;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public TransactionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TransactionStatusEnum status) {
        this.status = status;
    }

    @Column(name = "bank_reference")
    public String getBankReference() {
        return bankReference;
    }

    public void setBankReference(String bankReference) {
        this.bankReference = bankReference;
    }

    @JoinColumn(name = "bank_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TBankAccount getBank() {
        return bank;
    }

    public void setBank(TBankAccount bank) {
        this.bank = bank;
    }

    @Column(name = "pay_slip_image_path")
    public String getPayslipImagePath() {
        return payslipImagePath;
    }

    public void setPayslipImagePath(String payslipImagePath) {
        this.payslipImagePath = payslipImagePath;
    }

    @Column(name = "pay_slip_timestamp")
    public LocalDateTime getPayslipTimestamp() {
        return payslipTimestamp;
    }

    public void setPayslipTimestamp(LocalDateTime payslipTimestamp) {
        this.payslipTimestamp = payslipTimestamp;
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

    @JoinColumn(name = "created_by",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TUser createdBy) {
        this.createdBy = createdBy;
    }

    @JoinColumn(name = "modified_by",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(TUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
