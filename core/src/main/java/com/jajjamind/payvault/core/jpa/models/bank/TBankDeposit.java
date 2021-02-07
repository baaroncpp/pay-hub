package com.jajjamind.payvault.core.jpa.models.bank;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.TransactionStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author akena
 * 16/12/2020
 * 00:35
 **/
@Entity
@Table(name = "t_bank_deposits",schema = "core")
public class TBankDeposit extends AuditedEntity {

    private TAgent agent;
    private BigDecimal amountDeposited;
    private TransactionStatusEnum status;
    private String bankReference;
    private TBankAccount bank;
    private String payslipImagePath;
    private Date payslipTimestamp;
    private String externalDepositorName;

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

    @JoinColumn(name = "bank_id",referencedColumnName = "id",insertable = true,updatable = true)
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
    public Date getPayslipTimestamp() {
        return payslipTimestamp;
    }

    public void setPayslipTimestamp(Date payslipTimestamp) {
        this.payslipTimestamp = payslipTimestamp;
    }

    @Column(name = "depositor")
    public String getExternalDepositorName() {
        return externalDepositorName;
    }

    public void setExternalDepositorName(String externalDepositorName) {
        this.externalDepositorName = externalDepositorName;
    }
}
