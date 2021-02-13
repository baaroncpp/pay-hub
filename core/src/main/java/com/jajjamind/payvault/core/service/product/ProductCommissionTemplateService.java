package com.jajjamind.payvault.core.service.product;

import com.jajjamind.payvault.core.api.product.models.ProductCommissionTemplate;
import com.jajjamind.payvault.core.service.BaseApiService;

import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 23:14
 **/
public interface ProductCommissionTemplateService extends BaseApiService<ProductCommissionTemplate> {

   ProductCommissionTemplate getByName(String name);
   List<ProductCommissionTemplate> getTariffGroup(String groupIdentifier);

   void disableTariffGroup(String groupIdentifier);
}
