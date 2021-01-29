package com.payhub.interswitch.constants;

import org.springframework.stereotype.Component;

@Component
public class PayvaultConstants {
	
	public static String INTERSWITCH_BASE_URL = "https://interswitch.io/";
	/*
	@Value("${interswitch.base.url}")
	public void setINTERSWITCH_BASE_URL(String value) {
		PayvaultConstants.INTERSWITCH_BASE_URL = value;
	}
	
	@Value("${payvault.core.base.url}")
	public void setPAYVAULT_CORE_BASE_URL(String value) {
		PayvaultConstants.PAYVAULT_CORE_BASE_URL = value;
	}
	*/
}
