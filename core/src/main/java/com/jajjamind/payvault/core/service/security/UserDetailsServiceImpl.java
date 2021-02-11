package com.jajjamind.payvault.core.service.security;

import com.jajjamind.commons.exceptions.ErrorMessageConstants;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.user.TGroupAuthority;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.security.UserRepository;
import com.jajjamind.payvault.core.repository.user.GroupAuthorityRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 25/11/2020
 * 10:52
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupAuthorityRepository groupAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<TUser> tUserOptional = userRepository.findByUsername(username);
        Validate.isTrue(tUserOptional.isPresent(), ErrorMessageConstants.USER_NOT_FOUND,username);

        return populateUserDetails(tUserOptional.get());

    }

    private LoggedInUser populateUserDetails(TUser tUser){
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setAccountNonExpired(!tUser.isAccountExpired());
        loggedInUser.setAccountNonLocked(!tUser.isAccountLocked());
        loggedInUser.setCredentialExpired(!tUser.isCredentialExpired());
        loggedInUser.setEnabled(Boolean.TRUE);
        loggedInUser.setId(tUser.getId());
        loggedInUser.setPassword(tUser.getPassword());
        loggedInUser.setUsername(tUser.getUsername());
        loggedInUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(tUser.getUserAuthority().getAuthority()));

        //Find additional authorities from group
        Optional<TGroupAuthority> groupAuthority = groupAuthorityRepository.findGroupAuthorityFromUserName(loggedInUser.getUsername());

        if(groupAuthority.isPresent()){
            List<GrantedAuthority> allAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(groupAuthority.get().getAuthority());
            allAuthorities.addAll(loggedInUser.getAuthorities());

            loggedInUser.setAuthorities(allAuthorities);
        }

        return loggedInUser;

    }

}
