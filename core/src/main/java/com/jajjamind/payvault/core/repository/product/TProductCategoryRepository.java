package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author akena
 * 15/02/2021
 * 16:08
 **/
@Repository
public interface TProductCategoryRepository extends CrudRepository<TProductCategory,Integer> {

}
