package com.jajjamind.payvault.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
