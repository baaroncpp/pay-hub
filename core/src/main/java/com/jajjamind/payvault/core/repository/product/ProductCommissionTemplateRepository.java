package com.jajjamind.payvault.core.repository.product;

import com.jajjamind.payvault.core.jpa.models.product.TProductCommissionTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommissionTemplateRepository extends CrudRepository<Long, TProductCommissionTemplate> {
}
