package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCharge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductChargeRepository extends CrudRepository<TProductCharge,Long> {

    @Query("SELECT u from TProductCharge u WHERE u.tariffGroupIdentifier = :identifier AND u.chargeType = 'TARIFF' and u.nonActive = false")
    public List<TProductCharge> findTariffChargeByGroupIdentifier(@Param("identifier") String identifier);
}
