package com.payhub.mobilemoney.config;

import com.payhub.mobilemoney.airtel.network.AirtelMoneyApiService;
import com.payhub.mobilemoney.airtel.network.RetrofitAirtelMoneyService;
import com.payhub.mobilemoney.mtn.network.MtnAPIService;
import com.payhub.mobilemoney.mtn.network.RetrofitMTNService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MobileMoneyConfig {

    @Bean
    public AirtelMoneyApiService getAirtelMoneyApiService(){
        return RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
    }

    @Bean
    public MtnAPIService getMtnAPIService(){
        return RetrofitMTNService.getMtnAPIService();
    }

}
