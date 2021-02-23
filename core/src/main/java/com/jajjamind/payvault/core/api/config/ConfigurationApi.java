package com.jajjamind.payvault.core.api.config;

import com.jajjamind.payvault.core.api.config.models.ProviderConfiguration;
import com.jajjamind.payvault.core.service.config.ProviderConfigService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 22/02/2021
 * 12:23
 **/
@Tag(name = "Configuration",description = "Manage configurations on PayHub")
@RolesAllowed("ROLE_ADMIN")
@RestController
@RequestMapping("/config")
public class ConfigurationApi {

    @Autowired
    ProviderConfigService service;

    @PutMapping(name = "/provider")
    public ProviderConfiguration updateConfiguration(@RequestBody ProviderConfiguration providerConfiguration){
        return service.updateProviderConfiguration(providerConfiguration);
    }

    @GetMapping("/provider")
    public List<ProviderConfiguration> getProviderConfigs(){
        return service.getProviderConfiguration();
    }

}
