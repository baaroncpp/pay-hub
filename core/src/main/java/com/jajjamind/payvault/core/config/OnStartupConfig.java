package com.jajjamind.payvault.core.config;

import com.jajjamind.commons.utils.configs.ProviderConfiguration;
import com.jajjamind.commons.utils.configs.ProviderMap;
import com.jajjamind.payvault.core.jpa.models.config.TProviderConfiguration;
import com.jajjamind.payvault.core.repository.config.ProviderConfigRepository;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author akena
 * 21/02/2021
 * 19:39
 **/
@Component
@Order(0)
public class OnStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ProviderConfigRepository providerConfigRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<TProviderConfiguration> tConfigs = providerConfigRepository.findAll();
        Map<String,ProviderConfiguration> configs = new HashMap<>();

        tConfigs.forEach(t -> {
            ProviderConfiguration p = new ProviderConfiguration();
            BeanUtilsCustom.copyProperties(t,p);

            configs.put(t.getName(),p);
        });

        ProviderMap.setConfigurationMap(configs);

    }
}
