package com.jajjamind.commons.utils;

import org.springframework.boot.system.SystemProperties;

/**
 * @author akena
 * 21/02/2021
 * 04:48
 **/
public class EnvironmentUtils {

    private static final String DEVELOPMENT = "DEVELOPMENT";
    private static final String DEVELOPMENT_ALT = "DEV";
    private static final String STAGING = "STAGING";
    private static final String CI_CD = "CICD";
    private static final String PRODUCTION = "PRODUCTION";
    private static final String PRODUCTION_ALT = "PROD";

    final String environment = SystemProperties.get("environment");

    public boolean isDevelopment(){
        return environment.equalsIgnoreCase(DEVELOPMENT) || environment.equalsIgnoreCase(DEVELOPMENT_ALT);
    }


    public boolean isProduction(){
        return environment.equalsIgnoreCase(PRODUCTION) || environment.equalsIgnoreCase(PRODUCTION_ALT);
    }

    public boolean isCIProcess(){
        return environment.equalsIgnoreCase(CI_CD);
    }


    public boolean isStaging(){
        return environment.equalsIgnoreCase(STAGING);
    }
}
