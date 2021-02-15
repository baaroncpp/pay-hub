package com.jajjamind.payvault.core.api.product;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.product.models.Product;
import com.jajjamind.payvault.core.api.product.models.ProductCategory;
import com.jajjamind.payvault.core.api.product.models.ProductCommissionTemplate;
import com.jajjamind.payvault.core.exception.ServiceApiNotSupported;
import com.jajjamind.payvault.core.service.product.ProductCommissionTemplateService;
import com.jajjamind.payvault.core.service.product.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:16
 **/

@Tag(name = "Product",description = "Manage product templates, products and product commissions for providers")
@RestController
@RequestMapping("/product")
public class ProductApi implements BaseApi<Product> {

    @Autowired
    public ProductService productService;

    @Autowired
    public ProductCommissionTemplateService commissionTemplateService;

    @RolesAllowed("ROLE_PRODUCT.WRITE")
    @Override
    public Product add( @Nullable  Product product) {

        return productService.add(product);
    }

    @RolesAllowed({"ROLE_PRODUCT.READ","ROLE_PRODUCT.WRITE"})
    @Override
    public Product get(Long id) {
        return productService.get(id);
    }

    @RolesAllowed("ROLE_PRODUCT.WRITE")
    @Override
    public Product update(Product product) {
        return productService.update(product);
    }

    @RolesAllowed("ROLE_PRODUCT.WRITE")
    @Override
    public Product delete(long id) {
        throw new ServiceApiNotSupported("Product deletion is not implemented");
    }

    @Override
    public List<Product> getAll() {

        return productService.getAll();
    }

    @GetMapping(value = "/all/by-category",produces = APPLICATION_JSON)
    public List<ProductCategory> getAllByProductCategory() {

        return productService.getWithCategoryGrouping();
    }


    @RolesAllowed({"ROLE_PRODUCT.ENABLE","ROLE_PRODUCT.WRITE"})
    @PostMapping("/{id}/disable")
    public void disableProduct(@PathVariable("id") Long id){
        productService.deactivateProduct(id);
    }

    @RolesAllowed({"ROLE_PRODUCT.DISABLE","ROLE_PRODUCT.WRITE"})
    @PostMapping("/{id}/enable")
    public void enableProduct(@PathVariable("id") Long id){
        productService.activateProduct(id);
    }

    @RolesAllowed("ROLE_PRODUCT_TEMPLATE.WRITE")
    @PostMapping("/commission/template")
    public ProductCommissionTemplate addCommissionTemplate(@RequestBody ProductCommissionTemplate t){
        return commissionTemplateService.add(t);
    }

    @RolesAllowed({"ROLE_PRODUCT_TEMPLATE.READ","ROLE_PRODUCT_WRITE"})
    @GetMapping("/commission/template/{id}")
    public ProductCommissionTemplate getCommissionTemplate(@PathVariable("id") Long id){
        return commissionTemplateService.get(id);
    }

    @RolesAllowed({"ROLE_PRODUCT_TEMPLATE.DELETE","ROLE_PRODUCT_WRITE"})
    @DeleteMapping("/commission/template/{id}")
    public ProductCommissionTemplate deleteCommissionTemplate(@PathVariable("id") Long id){
        return commissionTemplateService.delete(id);
    }

    @RolesAllowed({"ROLE_PRODUCT_TEMPLATE.DELETE","ROLE_PRODUCT_WRITE"})
    @GetMapping("/commission/template/tariff/{tariffName}")
    public List<ProductCommissionTemplate> getTariffGroup(@PathVariable("tariffName") String tariffName){
        return commissionTemplateService.getTariffGroup(tariffName);
    }

    @RolesAllowed({"ROLE_PRODUCT_TEMPLATE.DELETE","ROLE_PRODUCT_WRITE"})
    @DeleteMapping("/commission/template/tariff/disable/{tariffName}")
    public void disableTariffGroup(@PathVariable("tariffName") String tariffName){
        commissionTemplateService.disableTariffGroup(tariffName);
    }

    @RolesAllowed("ROLE_PRODUCT_TEMPLATE.WRITE")
    @GetMapping("/commission/template")
    public List<ProductCommissionTemplate> getAllTemplates(){
        return commissionTemplateService.getAll();
    }

    @RolesAllowed("ROLE_PRODUCT_TEMPLATE.WRITE")
    @PutMapping("/commission/template")
    public ProductCommissionTemplate updateCommissionTemplate(@RequestBody ProductCommissionTemplate request){
        return commissionTemplateService.update(request);
    }

}
