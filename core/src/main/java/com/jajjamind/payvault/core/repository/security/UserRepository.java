package com.jajjamind.payvault.core.repository.security;

import com.jajjamind.payvault.core.jpa.models.user.TUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author akena
 * 25/11/2020
 * 10:55
 **/
public interface UserRepository extends CrudRepository<TUser,Long> {

    @Query("Select u from TUser u where u.username = :username")
    Optional<TUser> findByUsername(@Param("username")String username);

}
