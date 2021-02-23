package com.payhub.notification.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jajjamind.commons.utils.configs.Provider;
import com.payhub.notification.constants.NotificationType;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Notification.class)
public class Notification {
    private NotificationType notificationType;
    private String subject;
    private String content;
    private String address;
    private String attachmentPath;
    private String maskedContent;
    private Provider provider;

    public Notification() {
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAttachmentPath() {
        return attachmentPath;
    }


    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getMaskedContent() {
        return maskedContent;
    }

    public void setMaskedContent(String maskedContent) {
        this.maskedContent = maskedContent;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
