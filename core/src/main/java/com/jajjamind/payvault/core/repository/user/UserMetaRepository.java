package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 15:48
 **/
@Repository
public interface UserMetaRepository extends CrudRepository<TUserMeta,Long> {
}
