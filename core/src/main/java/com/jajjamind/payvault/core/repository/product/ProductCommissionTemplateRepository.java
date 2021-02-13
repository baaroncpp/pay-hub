package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommissionTemplate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCommissionTemplateRepository extends CrudRepository<TProductCommissionTemplate,Long> {

    @Query("SELECT u from TProductCommissionTemplate u WHERE u.tariffGroupIdentifier = :identifier AND u.pricingType = 'TARIFF' and u.status = true")
    List<TProductCommissionTemplate> findTariffChargeByGroupIdentifier(@Param("identifier") String identifier);

    Optional<TProductCommissionTemplate> findByName(@Param("name") String name);

    @Modifying
    @Query("UPDATE TProductCommissionTemplate u SET u.status = false WHERE u.tariffGroupIdentifier = :identifier")
    void deactivateTariffGroup(@Param("identifier") String identifier);

}
