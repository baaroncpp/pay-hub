package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCharge;
import org.springframework.data.repository.CrudRepository;

public interface ProductChargeRepository extends CrudRepository<Long, TProductCharge> {
}
