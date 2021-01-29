package com.payhub.mobilemoney.airtel.config;

import com.payhub.mobilemoney.airtel.network.AirtelMoneyApiService;
import com.payhub.mobilemoney.airtel.network.RetrofitAirtelMoneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirtelMoneyConfig {

    @Bean
    public AirtelMoneyApiService getAirtelMoneyApiService(){
        RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
    }

}
