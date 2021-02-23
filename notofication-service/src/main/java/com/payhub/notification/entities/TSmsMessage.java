package com.payhub.notification.entities;

import com.payhub.notification.constants.Status;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 21/02/2021
 * 13:38
 **/
@Entity
@Table(name = "t_sms_message",schema = "core")
public class TSmsMessage {

    private String id;
    private String reference;
    private String recipient;
    private String provider;
    private String content;
    private Status status;
    private String statusDescription;
    private boolean isDelivered;
    private Date createdOn;
    private Date modifiedOn;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "recipient")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Column(name = "provider")
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name="status_description")
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
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

    @Column(name = "reference")
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Column(name = "is_delivered")
    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
