package com.jajjamind.payvault.core.service.security;

import com.jajjamind.commons.exceptions.ErrorMessageConstants;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.user.TGroupAuthority;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.repository.agent.AgentRepository;
import com.jajjamind.payvault.core.repository.security.UserRepository;
import com.jajjamind.payvault.core.repository.user.GroupAuthorityRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
    AgentRepository agentRepository;

    @Autowired
    GroupAuthorityRepository groupAuthorityRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {

        if(request.getHeader("agent_login") != null){
            Optional<TAgent> agent = agentRepository.findByUserName(username);
            Validate.isTrue(agent.isPresent(), ErrorMessageConstants.USER_NOT_FOUND);

            return populateAgentDetails(agent.get());

        }else {

            Optional<TUser> tUserOptional = userRepository.findByUsername(username);
            Validate.isTrue(tUserOptional.isPresent(), ErrorMessageConstants.USER_NOT_FOUND);

            return populateUserDetails(tUserOptional.get());
        }

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



    private LoggedInUser populateAgentDetails(TAgent tUser){
        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setAccountNonExpired(Boolean.TRUE);
        loggedInUser.setAccountNonLocked(!tUser.getNonLocked());
        loggedInUser.setCredentialExpired(!tUser.getNonLockedPin());
        loggedInUser.setEnabled(tUser.getApprovalStatus().equals(ApprovalEnum.APPROVED));
        loggedInUser.setId(tUser.getId());
        loggedInUser.setPassword(tUser.getPin());
        loggedInUser.setUsername(tUser.getUsername());

        if(tUser.getInitialPasswordReset()) {
            loggedInUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_MOBILE.READ,ROLE_MOBILE.WRITE"));
        }else{
            loggedInUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_MOBILE_RESET_PIN.WRITE"));
        }

        return loggedInUser;

    }

}
