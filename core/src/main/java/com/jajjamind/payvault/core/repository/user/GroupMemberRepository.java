package com.jajjamind.payvault.core.repository.user;

import com.jajjamind.payvault.core.jpa.models.user.TGroupMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author akena
 * 26/01/2021
 * 17:58
 **/
public interface GroupMemberRepository extends CrudRepository<TGroupMember,Integer> {

    @Query("Select u from TGroupMember u where u.username = :username and u.groupId = :groupId")
    Optional<TGroupMember> findByUsernameAndGroup(@Param("username") String username,@Param("groupId") Integer groupId);
}
