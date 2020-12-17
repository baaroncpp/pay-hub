package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 17/12/2020
 * 13:09
 **/
@Repository
public interface ProductCommissionRepository extends CrudRepository<Long, TProductCommission> {
}
