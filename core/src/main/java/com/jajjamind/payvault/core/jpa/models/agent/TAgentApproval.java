package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 30/01/2021
 * 02:53
 **/
@Entity
@Table(name = "t_agent_approval",schema = "core")
public class TAgentApproval extends AuditedEntity {

    private TAgent agent;
    private TUser approver1;
    private TUser approver2;
    private Date firstApproveOn;
    private Date secondApproveOn;
    private ApprovalEnum status;
    private String note;
    private Integer approvalCount;

    @JoinColumn(name = "agent_id",referencedColumnName = "id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }

    @JoinColumn(name = "approver_1",referencedColumnName = "id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getApprover1() {
        return approver1;
    }


    public void setApprover1(TUser approver1) {
        this.approver1 = approver1;
    }

    @JoinColumn(name = "approver_2",referencedColumnName = "id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getApprover2() {
        return approver2;
    }

    public void setApprover2(TUser approver2) {
        this.approver2 = approver2;
    }

    @Column(name = "first_approve_on")
    @Temporal(TemporalType.DATE)
    public Date getFirstApproveOn() {
        return firstApproveOn;
    }

    public void setFirstApproveOn(Date firstApproveOn) {
        this.firstApproveOn = firstApproveOn;
    }

    @Column(name = "second_approve_on")
    @Temporal(TemporalType.DATE)
    public Date getSecondApproveOn() {
        return secondApproveOn;
    }

    public void setSecondApproveOn(Date secondApproveOn) {
        this.secondApproveOn = secondApproveOn;
    }

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    public ApprovalEnum getStatus() {
        return status;
    }

    public void setStatus(ApprovalEnum status) {
        this.status = status;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "approval_count")
    public Integer getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }
}
