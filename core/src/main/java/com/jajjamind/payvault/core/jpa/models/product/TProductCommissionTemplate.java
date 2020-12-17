package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.CommissionTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.CurrencyEnum;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author akena
 * 16/12/2020
 * 01:15
 **/
@Entity
@Table(name = "t_product_commission_template",schema = "core")
public class TProductCommissionTemplate extends AuditedEntity {

    private CommissionTypeEnum type;
    private BigDecimal amount;
    private float percent;
    private CurrencyEnum currency;
    private boolean active;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public CommissionTypeEnum getType() {
        return type;
    }

    public void setType(CommissionTypeEnum type) {
        this.type = type;
    }

    @Column(name = "flat_amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(name = "percent_amount")
    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
