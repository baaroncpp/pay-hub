package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author akena
 * 26/01/2021
 * 13:58
 **/
@Entity
@Table(name = "t_roles",schema = "core")
public class TRole extends BaseEntityInteger {
    private String name;
    private String note;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
