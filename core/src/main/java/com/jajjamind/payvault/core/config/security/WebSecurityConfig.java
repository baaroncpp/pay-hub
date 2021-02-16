package com.jajjamind.payvault.core.config.security;

import com.jajjamind.payvault.core.service.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author akena
 * 27/11/2020
 * 00:52
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SWAGGER_URL_PATHS = new String[] {
            "**/termsofuse",
            "/v3/api-docs/**",
            "/swagger-ui.html**","/swagger-ui/**",
            "/configuration/security","/configuration/ui","/swagger-resources/**",
            "/v2/api-docs**", "/webjars/**" };

    // A custom implementation of user details service backed by a database would be in order
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(SWAGGER_URL_PATHS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Authentication manager to link authorization service and users -- Not sure if i want this global
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

}
