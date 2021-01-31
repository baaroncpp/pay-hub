package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommissionTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCommissionTemplateRepository extends CrudRepository<TProductCommissionTemplate,Long> {

    @Query("SELECT u from TProductCharge u WHERE u.tariffGroupIdentifier = :identifier AND u.chargeType = 'TARIFF' and u.nonActive = false")
    public List<TProductCommissionTemplate> findTariffChargeByGroupIdentifier(@Param("identifier") String identifier);

    Optional<TProductCommissionTemplate> findByName(@Param("name") String name);

}
