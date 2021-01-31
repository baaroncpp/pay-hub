package com.jajjamind.payvault.core.service.product;

import com.jajjamind.payvault.core.api.product.models.ProductCommissionTemplate;
import com.jajjamind.payvault.core.service.BaseApiService;

/**
 * @author akena
 * 18/01/2021
 * 23:14
 **/
public interface ProductCommissionTemplateService extends BaseApiService<ProductCommissionTemplate> {

   ProductCommissionTemplate getByName(String name);
}
