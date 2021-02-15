package com.jajjamind.payvault.core.api.product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author akena
 * 15/02/2021
 * 16:40
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategory {

   private String name;
   private  Integer id;
   private List<Product> products;
}
