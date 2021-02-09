package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;

import javax.persistence.*;
import java.util.Date;

/**
 * @author akena
 * 13/12/2020
 * 13:26
 **/
public class TUserPreviousPassword extends BaseEntityLong {

    private TUser user;
    private String pin;
    private Date removalTime;
    private String note;

    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
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
    public Date getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(Date removalTime) {
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
