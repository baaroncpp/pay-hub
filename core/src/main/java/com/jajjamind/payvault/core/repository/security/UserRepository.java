package com.jajjamind.payvault.core.repository.security;

import com.jajjamind.payvault.core.jpa.models.user.TUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 25/11/2020
 * 10:55
 **/
@Repository
public interface UserRepository extends CrudRepository<TUser,Long> {

    @Query("Select u from TUser u where u.username = :username")
    Optional<TUser> findByUsername(@Param("username")String username);

    @Query("Select u from TUser u left join fetch u.userAuthority left join fetch u.userMeta userMeta where u.id = :id")
    Optional<TUser> findUserById(@Param("id") Long id);

    @Query("Select u from TUser u left join fetch u.userAuthority where u.id = :id")
    Optional<TUser> findUserByIdWithAuthority(@Param("id") Long id);

}
