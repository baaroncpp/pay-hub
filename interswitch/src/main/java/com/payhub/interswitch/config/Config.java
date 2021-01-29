package com.payhub.interswitch.config;

import java.util.Map;

import com.payhub.interswitch.network.InterSwitchAPIService;
import com.payhub.interswitch.network.RetrofitInterSwitchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public InterSwitchAPIService getInterSwitchAPIService() {
		Map<String, String> header = null;
		return RetrofitInterSwitchService.getInterSwitchAPIService(header);
	}
	
}
