package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author akena
 * 13/12/2020
 * 13:26
 **/
public class TUserPreviousPassword extends BaseEntityLong {

    private TUser user;
    private String pin;
    private LocalDateTime removalTime;
    private String note;

    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getAgent() {
        return user;
    }

    public void setAgent(TUser agent) {
        this.user = user;
    }

    @Column(name = "pin")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Column(name = "removal_time")
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(LocalDateTime removalTime) {
        this.removalTime = removalTime;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
