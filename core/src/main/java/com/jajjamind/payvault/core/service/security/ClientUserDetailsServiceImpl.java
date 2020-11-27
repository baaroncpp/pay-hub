package com.jajjamind.payvault.core.service.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author akena
 * 18/11/2020
 * 22:32
 **/
@Service
public class ClientUserDetailsServiceImpl implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}