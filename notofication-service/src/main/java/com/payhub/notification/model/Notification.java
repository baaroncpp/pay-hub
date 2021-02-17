package com.payhub.notification.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.payhub.notification.constants.NotificationType;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Notification.class)
public class Notification {
	private NotificationType type;
	private String subject;
	private String content;
	private String address;
	private String attachmentPath;

	public Notification() {
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
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
}
