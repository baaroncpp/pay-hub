package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author akena
 * 25/11/2020
 * 11:10
 **/
@Entity
@Table(name = "t_group",schema = "core")
public class TGroup extends BaseEntityInteger {
    private String name;
    private String note;
    private TGroupAuthority groupAuthority;

    @OneToOne(mappedBy = "group")
    public TGroupAuthority getGroupAuthority() {
        return groupAuthority;
    }

    public void setGroupAuthority(TGroupAuthority groupAuthority) {
        this.groupAuthority = groupAuthority;
    }

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
