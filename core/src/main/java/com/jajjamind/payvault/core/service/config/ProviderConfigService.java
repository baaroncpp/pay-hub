package com.jajjamind.payvault.core.service.config;

import com.jajjamind.payvault.core.api.config.models.ProviderConfiguration;

import java.util.List;

/**
 * @author akena
 * 22/02/2021
 * 12:33
 **/
public interface ProviderConfigService {

    ProviderConfiguration updateProviderConfiguration(ProviderConfiguration config);
    List<ProviderConfiguration> getProviderConfiguration();

}
