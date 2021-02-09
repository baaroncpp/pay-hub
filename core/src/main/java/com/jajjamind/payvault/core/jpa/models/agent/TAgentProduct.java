package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 15:05
 **/
@Entity
@Table(name = "t_agent_product",schema = "core")
public class TAgentProduct extends BaseEntityLong {

    private TProduct product;
    private Boolean nonActive;
    private TAgent agent;

    @JoinColumn(name = "product_code",referencedColumnName = "product_code",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    @Column(name = "non_active")
    public Boolean getNonActive() {
        return nonActive;
    }

    public void setNonActive(Boolean nonActive) {
        this.nonActive = nonActive;
    }

    @JoinColumn(name = "agent_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }
}
