package com.jajjamind.payvault.core.service.security;

import com.jajjamind.commons.exceptions.ErrorMessageConstants;
import com.jajjamind.commons.utils.SetUtils;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.security.UserRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author akena
 * 25/11/2020
 * 10:52
 **/
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<TUser> tUserOptional = userRepository.findByUsername(username);
        Validate.isTrue(tUserOptional.isPresent(), ErrorMessageConstants.USER_NOT_FOUND,username);

        return populateUserDetails(tUserOptional.get());

    }

    private UserDetails populateUserDetails(TUser tUser){
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setAccountNonExpired(!tUser.isAccountExpired());
        loggedInUser.setAccountNonLocked(!tUser.isAccountLocked());
        loggedInUser.setCredentialExpired(!tUser.isCredentialExpired());
        loggedInUser.setEnabled(Boolean.TRUE);
        loggedInUser.setId(tUser.getId());
        loggedInUser.setPassword(tUser.getPassword());
        loggedInUser.setUsername(tUser.getUsername());
        loggedInUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(tUser.getUserAuthority().getAuthority()));

        return loggedInUser;

    }

}
