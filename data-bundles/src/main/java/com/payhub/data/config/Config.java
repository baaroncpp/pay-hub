package com.payhub.data.config;

import com.payhub.data.airtel.network.DseApiService;
import com.payhub.data.airtel.network.RetrofitDseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = false, value = "classpath:data_bundles.properties")
public class Config {
    @Bean
    public DseApiService getInterSwitchAPIService() {
        return RetrofitDseService.getRetrofitDseService();
    }
}
