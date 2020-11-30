package com.jajjamind.payvault.core.jpa.models.user;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author akena
 * 25/11/2020
 * 12:01
 **/
@Entity
@Table(name = "t_authority",schema = "core")
public class TUserAuthority implements Serializable {

    private Integer id;
    private String username;
    private String authority;

    @NaturalId
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "authority")
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
