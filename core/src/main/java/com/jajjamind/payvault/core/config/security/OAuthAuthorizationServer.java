package com.jajjamind.payvault.core.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.List;
import java.util.Map;

/**
 * @author akena
 * 19/11/2020
 * 23:05
 **/
@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    // This defines the app that will be making requests on behalf of the user defined in userdetails service (from webconfig)
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        InMemoryClientDetailsService service = new InMemoryClientDetailsService();
//
//        BaseClientDetails cd =  new BaseClientDetails();
//        cd.setClientId("client");
//        cd.setClientSecret("secret");
//        cd.setScope(List.of("read"));
//        cd.setAuthorizedGrantTypes(List.of("password"));
//
//        service.setClientDetailsStore(
//                Map.of("client", cd));
//
//        clients.withClientDetails(service);

        //Shorter method
        clients.inMemory()
                .withClient("client")
                .secret("secret")
                .authorizedGrantTypes("password")
                .scopes("read");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}
