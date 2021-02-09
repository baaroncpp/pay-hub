package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 17/12/2020
 * 13:09
 **/
@Repository
public interface ProductCommissionRepository extends CrudRepository<TProductCommission,Long>,ProductCommissionRepositoryCustom {

    @Query("SELECT u from TProductCommission u WHERE u.tariffGroupIdentifier = :identifier AND u.pricingType = 'TARIFF' and u.status = 'ACTIVE'")
    List<TProductCommission> findTariffChargeByGroupIdentifier(@Param("identifier") String identifier);

    @Query("SELECT u from TProductCommission u inner join fetch u.product where u.product.nonActive = false AND u.status = 'ACTIVE'")
    Optional<TProductCommission> findActiveByProduct(@Param("productId") Long productId);

    @Query("Select u from TProductCommission u where u.tariffGroupIdentifier = :tariffGroup AND u.fromAmount >=  :amount and u.toAmount <= :amount")
    Optional<TProductCommission> findCorrectTariff(@Param("amount")BigDecimal amount, @Param("tariffGroup") String tariffGroup);
}
