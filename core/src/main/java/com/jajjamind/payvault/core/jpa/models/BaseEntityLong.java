package com.jajjamind.payvault.core.jpa.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 25/11/2020
 * 10:59
 **/
@MappedSuperclass
public class BaseEntityLong {
    private Long id;
    private Date createdOn;
    private Date modifiedOn;



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "created_on",insertable =false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "modified_on")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
