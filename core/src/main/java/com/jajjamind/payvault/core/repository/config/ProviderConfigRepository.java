package com.jajjamind.payvault.core.repository.config;

import com.jajjamind.payvault.core.jpa.models.config.TProviderConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 21/02/2021
 * 19:46
 **/
@Repository
public interface ProviderConfigRepository extends CrudRepository<TProviderConfiguration,Long> {
    Optional<TProviderConfiguration> findByName(@Param("name")String name);
}
