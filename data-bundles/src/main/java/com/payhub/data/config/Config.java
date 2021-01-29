package com.payhub.data.config;

import com.payhub.data.airtel.network.DseApiService;
import com.payhub.data.airtel.network.RetrofitDseService;
import com.payhub.data.mtn.network.MtnBundleApiService;
import com.payhub.data.mtn.network.RetrofitMtnBundlesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class Config {
    @Bean
    public DseApiService getInterSwitchAPIService() {
        return RetrofitDseService.getRetrofitDseService();
    }

    @Bean
    public MtnBundleApiService getMtnBundleApiService(){
        return RetrofitMtnBundlesService.getRetrofitDseService(null);
    }
}
