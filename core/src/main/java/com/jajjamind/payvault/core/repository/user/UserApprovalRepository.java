package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserApproval;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 27/01/2021
 * 03:38
 **/
@Repository
public interface UserApprovalRepository extends CrudRepository<TUserApproval,Long> {
}
