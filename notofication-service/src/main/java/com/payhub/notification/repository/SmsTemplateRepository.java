package com.payhub.notification.repository;

import com.payhub.notification.entities.TSmsTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsTemplateRepository extends CrudRepository<TSmsTemplate,Integer> {

    @Query("Select u from TSmsTemplate u WHERE u.name = :name and u.status = 'ACTIVE'")
    Optional<TSmsTemplate> findByNameActive(@Param("name") String name);
}
