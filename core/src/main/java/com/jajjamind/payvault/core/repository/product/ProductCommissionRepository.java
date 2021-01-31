package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author akena
 * 17/12/2020
 * 13:09
 **/
@Repository
public interface ProductCommissionRepository extends CrudRepository<TProductCommission,Long> {

    @Query("SELECT u from TProductCharge u WHERE u.tariffGroupIdentifier = :identifier AND u.chargeType = 'TARIFF' and u.nonActive = false")
    public List<TProductCommission> findTariffChargeByGroupIdentifier(@Param("identifier") String identifier);
}
