package com.jajjamind.payvault.core.repository.config;

import com.jajjamind.payvault.core.jpa.models.config.TConfiguration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author akena
 * 01/02/2021
 * 03:11
 **/
public interface ConfigurationRepository extends CrudRepository<TConfiguration, Long> {

    Optional<TConfiguration> findByName(String name);
}
