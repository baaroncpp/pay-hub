package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;

import javax.persistence.*;

/**
 * @author akena
 * 27/01/2021
 * 03:30
 **/
@Entity
@Table(name = "t_user_approval",schema = "core")
public class TUserApproval extends AuditedEntity {

    private Long userId;
    private ApprovalEnum status; //PENDING APPROVED REJECTED

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    public ApprovalEnum getStatus() {
        return status;
    }

    public void setStatus(ApprovalEnum status) {
        this.status = status;
    }
}
