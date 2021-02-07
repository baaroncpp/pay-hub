package com.jajjamind.payvault.core.jpa.models.config;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;

import javax.persistence.*;

/**
 * @author akena
 * 01/02/2021
 * 03:04
 **/
@Entity
@Table(name = "t_configuration",schema = "core")
public class TConfiguration extends AuditedEntity {
    private String name;
    private String value;
    private String defaultValue;
    private String category;
    private TCountry countryCode;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "actual_value")
    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "default_value")
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JoinColumn(name = "country_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.EAGER)
    public TCountry getCountryCode() {
        return countryCode;
    }

}
