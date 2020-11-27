package com.jajjamind.payvault.core.jpa.models.user;

import javax.persistence.*;

/**
 * @author akena
 * 25/11/2020
 * 12:01
 **/
@Entity
@Table(name = "t_authority",schema = "core")
public class TUserAuthority {

    private Integer id;
    private TUser user;
    private String authority;

    @JoinColumn(name = "username")
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
