package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<TGroup,Integer> {

    Optional<TGroup> findByName(@Param("name") String name);
}
