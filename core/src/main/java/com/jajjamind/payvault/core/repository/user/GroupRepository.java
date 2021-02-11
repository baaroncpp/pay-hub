package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<TGroup,Integer> {

    @Query("Select u from TGroup u left join fetch u.groupAuthority where lower(u.name) = lower(:name)")
    Optional<TGroup> findByName(@Param("name") String name);
}
