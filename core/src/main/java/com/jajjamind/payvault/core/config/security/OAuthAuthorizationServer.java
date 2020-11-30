package com.jajjamind.payvault.core.config.security;

import com.jajjamind.payvault.core.service.security.ClientUserDetailsServiceImpl;
import com.jajjamind.payvault.core.service.security.UserDetailsTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.List;

/**
 * @author akena
 * 19/11/2020
 * 23:05
 **/
@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationServer extends AuthorizationServerConfigurerAdapter {


    @Value("${jwt.certificate.password}")
    private String password;

    @Value("${jwt.certificate.private-key}")
    private String privateKey;

    @Value("${jwt.certificate.alias}")
    private String alias;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(
                List.of(new UserDetailsTokenEnhancer(),
                jwtAccessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(
            jwtAccessTokenConverter()
        );
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();

        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(
                        new ClassPathResource(privateKey),
                        password.toCharArray()
                );

        converter.setKeyPair(
                keyStoreKeyFactory.getKeyPair(alias));

        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new ClientUserDetailsServiceImpl());

    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
        .checkTokenAccess("isAuthenticated()");
    }
}
