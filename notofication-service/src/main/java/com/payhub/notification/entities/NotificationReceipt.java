package com.payhub.notification.entities;

import java.util.Date;


public class NotificationReceipt {
    private String id;
    private String reference;
    private Date date;

    public NotificationReceipt() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
