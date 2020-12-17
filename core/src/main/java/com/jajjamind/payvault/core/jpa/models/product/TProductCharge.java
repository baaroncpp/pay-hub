package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.ProductChargeTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author akena
 * 13/12/2020
 * 14:21
 **/
@Entity
@Table(name = "t_product_charge",schema = "core")
public class TProductCharge  extends AuditedEntity {

    private String name;
    private ProductChargeTypeEnum chargeTypeEnum;
    private BigDecimal chargeAmount;
    private Float chargePercent;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private String tariffGroupIdentifier;
    private Boolean nonActive;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public ProductChargeTypeEnum getChargeTypeEnum() {
        return chargeTypeEnum;
    }

    public void setChargeTypeEnum(ProductChargeTypeEnum chargeTypeEnum) {
        this.chargeTypeEnum = chargeTypeEnum;
    }

    @Column(name = "charge_amount")
    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @Column(name = "charge_percent")
    public Float getChargePercent() {
        return chargePercent;
    }

    public void setChargePercent(Float chargePercent) {
        this.chargePercent = chargePercent;
    }

    @Column(name = "from_amount")
    public BigDecimal getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(BigDecimal fromAmount) {
        this.fromAmount = fromAmount;
    }

    @Column(name = "to_amount")
    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    @Column(name = "tariff_group_id")
    public String getTariffGroupIdentifier() {
        return tariffGroupIdentifier;
    }

    public void setTariffGroupIdentifier(String tariffGroupIdentifier) {
        this.tariffGroupIdentifier = tariffGroupIdentifier;
    }

    @Column(name = "non_active")
    public Boolean getNonActive() {
        return nonActive;
    }

    public void setNonActive(Boolean nonActive) {
        this.nonActive = nonActive;
    }
}
