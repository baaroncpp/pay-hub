package com.jajjamind.payvault.core.config;

import com.zaxxer.hikari.HikariDataSource;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * @author akena
 * 19/11/2020
 * 00:28
 **/
@Configuration
@ComponentScan({"com.jajjamind.payvault.core","com.payhub.notification.*"})
@EntityScan({"com.jajjamind.payvault.core.jpa.*","com.payhub.notification.entities"})
@EnableJpaRepositories({"com.jajjamind.payvault.core.repository","com.payhub.notification.repository"})
public class AppConfig {

    @Autowired
    HikariDataSource dataSource;

    @Bean
    @ApplicationScope
    public OkHttpClient httpClient(){
        return new OkHttpClient();
    }

}
