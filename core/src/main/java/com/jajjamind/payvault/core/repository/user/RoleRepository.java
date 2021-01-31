package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 26/01/2021
 * 14:39
 **/
public interface RoleRepository extends CrudRepository<TRole,Integer> {

    @Query("Select u from TRole u where u.name in :names")
    List<TRole> getCountOfRolesThatMatchList(@Param("names") List<String> names);

    Optional<TRole> findByName(@Param("name") String name);
}
