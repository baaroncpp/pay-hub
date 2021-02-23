package com.jajjamind.payvault.core.service.config;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.text.StringUtil;
import com.jajjamind.commons.utils.EncryptUtil;
import com.jajjamind.payvault.core.api.config.models.ProviderConfiguration;
import com.jajjamind.payvault.core.jpa.models.config.TProviderConfiguration;
import com.jajjamind.payvault.core.repository.config.ProviderConfigRepository;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akena
 * 22/02/2021
 * 12:33
 **/
@Service
public class ProviderConfigServiceImpl implements ProviderConfigService{

    @Autowired
    ProviderConfigRepository repository;

    @Autowired
    AuditService auditService;

    private static final String PASSWORD_MASK = "*******";

    @Override
    public ProviderConfiguration updateProviderConfiguration(ProviderConfiguration config) {

        TProviderConfiguration currentConfig = repository.findByName(config.getName()).orElseThrow(() -> new BadRequestException("Configuration with name %s not found ",config.getName()));

        config.setId(currentConfig.getId());
        final String currentPassword = currentConfig.getPassword();
        final String currentApiKey = config.getApiKey();

        BeanUtilsCustom.copyProperties(config,currentConfig);

        if(!StringUtil.isEmpty(config.getApiKey()) && config.getApiKey().equalsIgnoreCase(PASSWORD_MASK)){
            currentConfig.setApiKey(EncryptUtil.encryptAES(config.getPassword()));
        }else{
            currentConfig.setApiKey(currentApiKey);
        }

        if(!StringUtil.isEmpty(config.getPassword()) && !config.getPassword().equalsIgnoreCase(PASSWORD_MASK)){
            currentConfig.setPassword(EncryptUtil.encryptAES(config.getPassword()));
        }else{
            currentConfig.setPassword(currentPassword);
        }

        auditService.stampAuditedEntity(currentConfig);
        repository.save(currentConfig);

        config.setPassword(PASSWORD_MASK);
        config.setApiKey(PASSWORD_MASK);

        return config;
    }

    @Override
    public List<ProviderConfiguration> getProviderConfiguration() {

        List<ProviderConfiguration> providerConfig = new ArrayList<>();

        repository.findAll().forEach(t -> {
            ProviderConfiguration p = new ProviderConfiguration();
            BeanUtilsCustom.copyProperties(t,p);
            p.setPassword(PASSWORD_MASK);
            p.setApiKey(PASSWORD_MASK);

            providerConfig.add(p);
        });


        return providerConfig;
    }
}
