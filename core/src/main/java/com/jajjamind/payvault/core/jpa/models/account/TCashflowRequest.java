package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.enums.CashFlowEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import com.jajjamind.payvault.core.utils.Money;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 05/02/2021
 * 06:12
 **/
@Entity
@Table(name = "t_cash_flow_request",schema = "core")
public class TCashflowRequest extends AuditedEntity {

    private String externalReference;
    private Money amount;
    private TAccountTransaction fromAccTransaction;
    private TAccountTransaction toAccTransaction;
    private TProduct product;
    private TAgent agent;
    private TAccount fromAccount; // Decreasing in value
    private TAccount toAccount; // Increasing in value
    private TUserMeta approver1;
    private TUserMeta approver2;
    private String note;
    private String note1;
    private String note2;
    private TUserMeta rejectedBy;
    private CashFlowEnum flowType;
    private Date firstApprovedOn;
    private Date secondApprovedOn;
    private Integer approvalCount;
    private ApprovalEnum status;

    @Column(name = "external_reference")
    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    @Column(name = "amount")
    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @JoinColumn(name = "from_acc_transaction_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccountTransaction getFromAccTransaction() {
        return fromAccTransaction;
    }

    public void setFromAccTransaction(TAccountTransaction fromAccTransaction) {
        this.fromAccTransaction = fromAccTransaction;
    }

    @JoinColumn(name = "to_acc_transaction_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccountTransaction getToAccTransaction() {
        return toAccTransaction;
    }

    public void setToAccTransaction(TAccountTransaction toAccTransaction) {
        this.toAccTransaction = toAccTransaction;
    }

    @JoinColumn(name = "product_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    @JoinColumn(name = "agent_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }

    @JoinColumn(name = "from_account_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(TAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    @JoinColumn(name = "approver_1",referencedColumnName = "user_id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserMeta getApprover1() {
        return approver1;
    }

    public void setApprover1(TUserMeta approver1) {
        this.approver1 = approver1;
    }

    @JoinColumn(name = "approver_2",referencedColumnName = "user_id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserMeta getApprover2() {
        return approver2;
    }

    public void setApprover2(TUserMeta approver2) {
        this.approver2 = approver2;
    }

    @Column(name = "note1")
    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    @Column(name = "")
    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    @JoinColumn(name = "rejected_by",referencedColumnName = "user_id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserMeta getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(TUserMeta rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    @Column(name = "flow_type")
    public CashFlowEnum getFlowType() {
        return flowType;
    }

    public void setFlowType(CashFlowEnum flowType) {
        this.flowType = flowType;
    }

    @Column(name = "first_approve_on")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFirstApprovedOn() {
        return firstApprovedOn;
    }

    public void setFirstApprovedOn(Date firstApprovedOn) {
        this.firstApprovedOn = firstApprovedOn;
    }

    @Column(name = "second_approve_on")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSecondApprovedOn() {
        return secondApprovedOn;
    }

    public void setSecondApprovedOn(Date secondApprovedOn) {
        this.secondApprovedOn = secondApprovedOn;
    }

    @Column(name = "approval_count")
    public Integer getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ApprovalEnum getStatus() {
        return status;
    }

    public void setStatus(ApprovalEnum status) {
        this.status = status;
    }

    @JoinColumn(name = "to_account_id",referencedColumnName = "id",insertable = true,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(TAccount toAccount) {
        this.toAccount = toAccount;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
