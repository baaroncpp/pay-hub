package com.jajjamind.payvault.core.jpa.models;

import com.jajjamind.payvault.core.jpa.models.user.TUser;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 02:13
 **/
@MappedSuperclass
public class AuditedEntity extends BaseEntityLong{

    private TUser createdBy;
    private TUser modifiedBy;

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
