package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.api.users.models.UserAuthority;
import com.jajjamind.payvault.core.jpa.models.user.TUserAuthority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author akena
 * 26/01/2021
 * 16:37
 **/
public interface UserAuthorityRepository extends CrudRepository<TUserAuthority,Integer> {
    Optional<TUserAuthority> findByUsername(@Param("username") String username);
}
