package com.jajjamind.payvault.core.config.security;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author akena
 * 21/01/2021
 * 18:33
 **/
@Component
public class CustomAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        // User is my custom UserDetails class
        final Map<String,Object> userMap = (Map)map.get("user");

        authentication.setDetails(userMap);
        return authentication;
    }

}
