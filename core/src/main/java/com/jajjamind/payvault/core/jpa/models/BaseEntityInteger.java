package com.jajjamind.payvault.core.jpa.models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 13/12/2020
 * 02:26
 **/
@MappedSuperclass
public class BaseEntityInteger {

    private Integer id;
    private Date createdOn;
    private Date modifiedOn;



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "created_on",insertable =true, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "modified_on",insertable = false,updatable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
