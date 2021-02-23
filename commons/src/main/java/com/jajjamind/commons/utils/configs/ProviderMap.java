package com.jajjamind.commons.utils.configs;

import com.jajjamind.commons.utils.Validate;

import java.util.HashMap;
import java.util.Map;


/**
 * @author akena
 * 21/02/2021
 * 19:03
 **/
public class ProviderMap {

    private static final Object LOCK = new Object();
    private static Map<String,ProviderConfiguration> configurationMap = new HashMap<>();

    public static Map<String, ProviderConfiguration> getConfigurationMap() {
        return configurationMap;
    }

    public static void setConfigurationMap(Map<String, ProviderConfiguration> configurationMap) {
        ProviderMap.configurationMap = configurationMap;
    }

    public static ProviderConfiguration getProviderConfigurationNotNull(String name){
        Validate.isTrue(configurationMap.containsKey(name),"Provider configuration could not be located");
        return ProviderMap.configurationMap.get(name);
    }

    public static ProviderConfiguration getProviderConfiguration(Provider provider){
        return ProviderMap.configurationMap.get(provider.name());
    }

    public static void reloadProviderConfiguration(Map<String,ProviderConfiguration> config){
        synchronized (LOCK){
            ProviderMap.configurationMap.clear();
            config.entrySet().forEach(t -> {
                ProviderMap.configurationMap.put(t.getKey(),t.getValue());
            });
        }
    }
}
