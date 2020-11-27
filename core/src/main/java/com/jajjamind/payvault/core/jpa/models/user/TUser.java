package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;

import javax.persistence.*;
import java.util.List;

/**
 * @author akena
 * 25/11/2020
 * 10:56
 **/
@Entity
@Table(name = "t_user",schema = "core")
public class TUser extends BaseEntityLong {

    private String username;
    private String password;
    private boolean accountLocked;
    private boolean accountExpired;
    private boolean credentialExpired;
    private TUserAuthority userAuthority;

    @Column(name = "username",unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "account_locked")
    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @Column(name = "account_expired")
    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    @Column(name = "cred_expired")
    public boolean isCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "user")
    public TUserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(TUserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }
}
