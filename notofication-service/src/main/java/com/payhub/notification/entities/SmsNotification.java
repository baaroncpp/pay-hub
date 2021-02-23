package com.payhub.notification.entities;

import com.payhub.notification.constants.SmsDelivery;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_sms_notification", schema = "notification")
public class SmsNotification {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "message")
    private String message;

    @Column(name = "sms_delivery")
    private SmsDelivery smsDelivery;

    @Column(name = "notification_type")
    private String notificationType;

    @Column(name = "reference")
    private String reference;

    @Column(name = "status")
    private String status;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    public SmsNotification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SmsDelivery getSmsDelivery() {
        return smsDelivery;
    }

    public void setSmsDelivery(SmsDelivery smsDelivery) {
        this.smsDelivery = smsDelivery;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
