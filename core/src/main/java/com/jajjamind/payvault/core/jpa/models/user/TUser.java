package com.jajjamind.payvault.core.jpa.models.user;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author akena
 * 25/11/2020
 * 10:56
 **/
@Entity
@Table(name = "t_user",schema = "core")
public class TUser extends BaseEntityLong implements Serializable {

    private String username;
    private String password;
    private boolean accountLocked;
    private boolean accountExpired;
    private boolean credentialExpired;
    private boolean approved;
    private boolean initialPasswordReset;
    private TUserAuthority userAuthority;
    private transient TUserMeta userMeta;
    private Boolean isDeleted;
    private Long approvedBy;


    @Column(name = "username")
    public String getUsername() {
        return this.username;
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

    @JoinColumn(name = "username",referencedColumnName = "username",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(TUserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @JoinColumn(name = "id",referencedColumnName = "user_id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUserMeta getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(TUserMeta userMeta) {
        this.userMeta = userMeta;
    }

    @Column(name = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Column(name = "is_deleted")
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "approved_by")
    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Column(name = "initial_password_reset")
    public boolean isInitialPasswordReset() {
        return initialPasswordReset;
    }

    public void setInitialPasswordReset(boolean initialPasswordReset) {
        this.initialPasswordReset = initialPasswordReset;
    }


}
