package com.jajjamind.payvault.core.api.product;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.product.models.Product;
import com.jajjamind.payvault.core.api.product.models.ProductCommissionTemplate;
import com.jajjamind.payvault.core.service.product.ProductCommissionTemplateService;
import com.jajjamind.payvault.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:16
 **/
@RestController
@RequestMapping("/product")
public class ProductApi implements BaseApi<Product> {

    @Autowired
    public ProductService productService;

    @Autowired
    public ProductCommissionTemplateService commissionTemplateService;

    @Override
    public Product add(Product product) {

        return productService.add(product);
    }

    @Override
    public Product get(Long id) {
        return productService.get(id);
    }

    @Override
    public Product update(Product product) {
        return productService.update(product);
    }

    @Override
    public Product delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Product> getAll() {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/commission/template")
    public ProductCommissionTemplate addCommissionTemplate(@RequestBody ProductCommissionTemplate t){
        return commissionTemplateService.add(t);
    }

    @GetMapping("/commission/template/{id}")
    public ProductCommissionTemplate getCommissionTemplate(@PathVariable("id") Long id){
        return commissionTemplateService.get(id);
    }

    @GetMapping("/commission/template")
    public List<ProductCommissionTemplate> getAllTemplates(){
        return commissionTemplateService.getAll();
    }
    @PutMapping("/commission/template")
    public ProductCommissionTemplate updateCommissionTemplate(@RequestBody ProductCommissionTemplate request){
        return commissionTemplateService.update(request);
    }

}
