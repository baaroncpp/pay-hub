package com.jajjamind.payvault.core.service.security;

import com.jajjamind.commons.utils.MapUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.time.ZoneId;
import java.util.Map;

/**
 * @author akena
 * 29/11/2020
 * 04:51
 **/
public class UserDetailsTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map info = MapUtils.create("generatedInZone", ZoneId.systemDefault().toString()).build();

        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
        info.put("user",authentication.getPrincipal());
        token.setAdditionalInformation(info);
        return token;

    }
}
