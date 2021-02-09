package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.enums.CurrencyEnum;
import com.jajjamind.payvault.core.jpa.models.enums.PricingTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author akena
 * 16/12/2020
 * 01:15
 **/
@Entity
@Table(name = "t_product_commission_template",schema = "core")
public class TProductCommissionTemplate extends TBasePricing {

    private PricingTypeEnum pricingType;
    private BigDecimal amount;
    private BigDecimal systemAmount;
    private float percent;
    private float systemPercent;
    private BigDecimal systemTariff;
    private CurrencyEnum currency;
    private Boolean status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public PricingTypeEnum getPricingType() {
        return pricingType;
    }

    public void setPricingType(PricingTypeEnum pricingType) {
        this.pricingType = pricingType;
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
    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "system_flat_amount")
    public BigDecimal getSystemAmount() {
        return systemAmount;
    }

    public void setSystemAmount(BigDecimal systemAmount) {
        this.systemAmount = systemAmount;
    }

    @Column(name = "system_percent_amount")
    public float getSystemPercent() {
        return systemPercent;
    }


    public void setSystemPercent(float systemPercent) {
        this.systemPercent = systemPercent;
    }

    @Column(name = "system_tariff")
    public BigDecimal getSystemTariff() {
        return systemTariff;
    }

    public void setSystemTariff(BigDecimal systemTariff) {
        this.systemTariff = systemTariff;
    }
}
