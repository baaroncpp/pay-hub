package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;

import java.util.Optional;

/**
 * @author akena
 * 08/02/2021
 * 11:51
 **/
public interface ProductCommissionRepositoryCustom {

    Optional<TProductCommission> findProductCommissionGeneric( Long productId);
}
