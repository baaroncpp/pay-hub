package com.jajjamind.payvault.core.jpa.models.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author akena
 * 25/11/2020
 * 11:12
 **/
@Entity
@Table(name = "t_group_authority",schema = "core")
public class TGroupAuthority {
    @Id
    private long groupId;
    private String authority;

    @Column(name = "group_id")
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
