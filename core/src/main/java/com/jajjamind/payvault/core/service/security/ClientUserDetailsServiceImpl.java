package com.jajjamind.payvault.core.service.security;

import com.jajjamind.commons.exceptions.ErrorMessageConstants;
import com.jajjamind.commons.utils.SetUtils;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.jpa.models.appclient.TAppClient;
import com.jajjamind.payvault.core.repository.security.AppClientRepository;
import com.jajjamind.payvault.core.security.models.ClientApp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author akena
 * 18/11/2020
 * 22:32
 **/
@Service
public class ClientUserDetailsServiceImpl implements ClientDetailsService {


    @Autowired
    AppClientRepository appClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {

        Optional<TAppClient> appClient = appClientRepository.getByName(clientId);

        Validate.isTrue(appClient.isPresent(), ErrorMessageConstants.APP_CLIENT_NOT_FOUND,clientId);

        TAppClient client = null;
        if(appClient.isPresent()){
            client = appClient.get();
        }

        Validate.isTrue(client !=null && client.getEnabled(),ErrorMessageConstants.APP_CLIENT_NOT_ENABLED,clientId);

        return getClientDetails(client);
    }

    private ClientDetails getClientDetails(TAppClient appClient){

        ClientApp details = new ClientApp();
        BeanUtils.copyProperties(appClient,details);
        details.setAuthorizedGrantTypes(SetUtils.getSetFromStringWithSeparator(appClient.getGrantTypes()));
        details.setRegisteredRedirectUri(null);
        details.setScopes(SetUtils.getSetFromStringWithSeparator(appClient.getScope()));
        details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(appClient.getAuthorities()));
        details.setRefreshTokenValiditySeconds(appClient.getTokenValidity());
        details.setAccessTokenValiditySeconds(appClient.getTokenValidity());

        return details;

    }

}