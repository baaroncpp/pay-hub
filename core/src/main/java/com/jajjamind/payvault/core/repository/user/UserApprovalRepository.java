package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserApproval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author akena
 * 27/01/2021
 * 03:38
 **/
@Repository
public interface UserApprovalRepository extends CrudRepository<TUserApproval,Long> {

    @Query("Select u from TUserApproval u WHERE u.userId = :id and u.status ='PENDING'")
    Optional<TUserApproval> findByUserIdForApproval(@Param("id") Long id);
}
