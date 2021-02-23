package com.payhub.notification.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 21/02/2021
 * 13:38
 **/
@Entity
@Table(name = "t_sms_template",schema = "core")
public class TSmsTemplate {
    private Integer id;
    private String name;
    private String category;
    private String status;
    private String content;
    private Date createdOn;
    private Date modifiedOn;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
