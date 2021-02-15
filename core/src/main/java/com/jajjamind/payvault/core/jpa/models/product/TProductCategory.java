package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;

import javax.persistence.*;
import java.util.List;

/**
 * @author akena
 * 15/02/2021
 * 13:59
 **/

@Entity
@Table(name = "t_product_category",schema = "core")
public class TProductCategory extends BaseEntityInteger {

    private String name;
    private String note;
    private List<TProduct> products;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(mappedBy = "productCategory",fetch = FetchType.EAGER)
    public List<TProduct> getProducts() {
        return products;
    }

    public void setProducts(List<TProduct> products) {
        this.products = products;
    }
}

