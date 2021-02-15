package com.jajjamind.payvault.core.service.product;

import com.jajjamind.payvault.core.api.product.models.Product;
import com.jajjamind.payvault.core.api.product.models.ProductCategory;
import com.jajjamind.payvault.core.service.BaseApiService;

import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:23
 **/
public interface ProductService extends BaseApiService<Product> {

    void deactivateProduct(Long id);
    void activateProduct(Long id);
    List<ProductCategory> getWithCategoryGrouping();
}
