package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserAuthority;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 26/01/2021
 * 16:37
 **/
public interface UserAuthorityRepository extends CrudRepository<TUserAuthority,Integer> {
}
