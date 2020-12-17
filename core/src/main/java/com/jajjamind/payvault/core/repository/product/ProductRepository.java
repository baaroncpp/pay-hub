package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import org.springframework.data.repository.CrudRepository;

/**
 * @author akena
 * 17/12/2020
 * 13:08
 **/
public interface ProductRepository extends CrudRepository<Long, TProduct> {
}
