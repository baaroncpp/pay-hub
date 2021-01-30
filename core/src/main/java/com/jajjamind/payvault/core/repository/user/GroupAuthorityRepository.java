package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TGroupAuthority;
import org.springframework.data.repository.CrudRepository;

public interface GroupAuthorityRepository extends CrudRepository<TGroupAuthority,Integer> {
}
