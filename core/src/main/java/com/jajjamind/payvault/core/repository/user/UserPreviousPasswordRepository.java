package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserPreviousPassword;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 09/02/2021
 * 14:14
 **/
public interface UserPreviousPasswordRepository extends CrudRepository<TUserPreviousPassword,Long> {
}
