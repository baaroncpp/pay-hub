package com.jajjamind.payvault.core.jpa.models.user;

import javax.persistence.*;

/**
 * @author akena
 * 26/01/2021
 * 17:56
 **/
@Entity
@Table(name = "t_group_members",schema = "core")
public class TGroupMember {


    private Integer id;
    private String username;
    private Integer groupId;

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
