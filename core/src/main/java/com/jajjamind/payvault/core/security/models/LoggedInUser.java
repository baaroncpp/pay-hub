package com.jajjamind.payvault.core.security.models;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author akena
 * 25/11/2020
 * 12:37
 **/
@lombok.Setter
public class LoggedInUser implements UserDetails {

    @Getter
    private long id;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private List<GrantedAuthority> authorities;
    private boolean enabled;
    private boolean credentialExpired;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
