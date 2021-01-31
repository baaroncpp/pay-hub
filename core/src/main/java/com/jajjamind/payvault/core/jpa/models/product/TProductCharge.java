package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author akena
 * 13/12/2020
 * 14:21
 **/
@Entity
@Table(name = "t_product_charge",schema = "core")
public class TProductCharge  extends TBasePricing {

    private PricingTypeEnum chargeType;
    private BigDecimal amount;
    private Float percent;
    private Boolean nonActive;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public PricingTypeEnum getChargeType() {
        return chargeType;
    }

    public void setChargeType(PricingTypeEnum chargeType) {
        this.chargeType = chargeType;
    }

    @Column(name = "charge_amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "charge_percent")
    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    @Column(name = "non_active")
    public Boolean getNonActive() {
        return nonActive;
    }

    public void setNonActive(Boolean nonActive) {
        this.nonActive = nonActive;
    }
}
