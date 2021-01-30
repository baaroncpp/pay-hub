package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * @author akena
 * 19/01/2021
 * 02:06
 **/
@MappedSuperclass
public class TBasePricing extends AuditedEntity {

    private BigDecimal fromAmount;
    private BigDecimal toAmount;
    private String tariffGroupIdentifier;
    private String name;
    private String note;

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
}
