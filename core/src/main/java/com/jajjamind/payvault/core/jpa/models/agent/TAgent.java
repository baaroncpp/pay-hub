package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.enums.AgentTypeEnum;
import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;

import javax.persistence.*;
import java.util.Date;


/**
 * @author akena
 * 13/12/2020
 * 02:50
 **/
@Entity
@Table(name = "t_agent",schema = "core")
public class TAgent extends AuditedEntity {

    private AgentTypeEnum type;
    private TUser approvedBy;
    private String externalId;
    private String pin;
    private String username;
    private Date activatedOn;
    private Boolean nonLocked;
    private Boolean nonDisabled;
    private Boolean nonLockedPin;
    private Date pinLastedUpdatedOn;
    private String lastPinLockReason;
    private TTermsAndConditions termsAndConditions;
    private TCompany company;
    private TUserMeta userMeta;
    private TAgent enrolledBy;
    private Date reactivatedOn;
    private ApprovalEnum approvalStatus;
    private Boolean initialPasswordReset;

    @JoinColumn(name = "id",referencedColumnName = "agent_id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserMeta getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(TUserMeta userMeta) {
        this.userMeta = userMeta;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public AgentTypeEnum getType() {
        return type;
    }

    public void setType(AgentTypeEnum type) {
        this.type = type;
    }

    @JoinColumn(name = "approved_by",referencedColumnName = "id",insertable = false,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(TUser approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Column(name = "external_id")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Column(name = "pin")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "activated_on")
    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }

    @Column(name = "non_locked")
    public Boolean getNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(Boolean nonLocked) {
        this.nonLocked = nonLocked;
    }

    @Column(name = "non_disabled")
    public Boolean getNonDisabled() {
        return nonDisabled;
    }

    public void setNonDisabled(Boolean nonDisabled) {
        this.nonDisabled = nonDisabled;
    }

    @Column(name = "non_locked_pin")
    public Boolean getNonLockedPin() {
        return nonLockedPin;
    }

    public void setNonLockedPin(Boolean nonLockedPin) {
        this.nonLockedPin = nonLockedPin;
    }

    @Column(name = "pin_last_updated_on")
    public Date getPinLastedUpdatedOn() {
        return pinLastedUpdatedOn;
    }

    public void setPinLastedUpdatedOn(Date pinLastedUpdatedOn) {
        this.pinLastedUpdatedOn = pinLastedUpdatedOn;
    }

    @Column(name = "last_pin_lock_reason")
    public String getLastPinLockReason() {
        return lastPinLockReason;
    }

    public void setLastPinLockReason(String lastPinLockReason) {
        this.lastPinLockReason = lastPinLockReason;
    }

    @JoinColumn(name = "terms_and_conditions",referencedColumnName = "id",insertable = true,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TTermsAndConditions getTermsAndConditions() {
        return termsAndConditions;
    }


    public void setTermsAndConditions(TTermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    @JoinColumn(name = "company_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TCompany getCompany() {
        return company;
    }

    public void setCompany(TCompany company) {
        this.company = company;
    }

    @JoinColumn(name = "enrolled_by",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getEnrolledBy() {
        return enrolledBy;
    }

    public void setEnrolledBy(TAgent enrolledBy) {
        this.enrolledBy = enrolledBy;
    }

    @Column(name = "last_reactivated_on")
    public Date getReactivatedOn() {
        return reactivatedOn;
    }

    public void setReactivatedOn(Date reactivatedOn) {
        this.reactivatedOn = reactivatedOn;
    }

    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    public ApprovalEnum getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalEnum approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Column(name = "initial_password_reset")
    public Boolean getInitialPasswordReset() {
        return initialPasswordReset;
    }

    public void setInitialPasswordReset(Boolean initialPasswordReset) {
        this.initialPasswordReset = initialPasswordReset;
    }
}
