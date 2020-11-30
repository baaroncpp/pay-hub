package com.jajjamind.payvault.core.repository.security;

import com.jajjamind.payvault.core.jpa.models.appclient.TAppClient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author akena
 * 29/11/2020
 * 21:46
 **/
public interface AppClientRepository extends CrudRepository<TAppClient,Long> {

    Optional<TAppClient> getByName(String name);
}
