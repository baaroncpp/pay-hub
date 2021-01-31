package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 17/12/2020
 * 15:48
 **/
@Repository
public interface UserMetaRepository extends CrudRepository<TUserMeta,Long> {

    @Query("Select u from TUserMeta u where u.userId is not null")
    public List<TUserMeta> findAllWithUserId();

    @Query("Select u from TUserMeta u inner join TUserApproval t on t.userId = u.userId and t.status = 'PENDING'")
    public List<TUserMeta> findAllWithUsersPendingApproval();

    public Optional<TUserMeta> findByUserId(@Param("userId") Integer userId);

}
