package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TGroupAuthority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupAuthorityRepository extends CrudRepository<TGroupAuthority,Integer> {

    @Query(value = "SELECT * from core.t_group_authority t " +
            "inner join core.t_group g on t.group_id = g.id " +
            "inner join core.t_group_members m on t.group_id = m.group_id and m.username = :username",nativeQuery = true)
    Optional<TGroupAuthority> findGroupAuthorityFromUserName(@Param("username") String username);
}
