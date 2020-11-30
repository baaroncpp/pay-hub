package com.jajjamind.payvault.core.config;

import com.jajjamind.payvault.core.service.security.ClientUserDetailsServiceImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * @author akena
 * 19/11/2020
 * 00:28
 **/
@Configuration
public class AppConfig {

    @Autowired
    HikariDataSource dataSource;

}
