package com.jajjamind.payvault.core.repository.agent;

/**
 * @author akena
 * 13/01/2021
 * 00:36
 **/

import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<TCountry,Integer>{

    @Query("Select u from TCountry u where u.isoAlpha2 = :alpha2Code")
    Optional<TCountry> findByAlpha2Code(@Param("alpha2Code")String alpha2Code);

}
