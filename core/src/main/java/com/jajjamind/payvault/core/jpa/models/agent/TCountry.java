package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.BaseEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author akena
 * 13/12/2020
 * 02:24
 **/
@Entity
@Table(name = "t_country", schema = "core")
public class TCountry extends BaseEntityInteger {

    private String name;
    private String isoAlpha2;
    private String isoAlpha3;
    private Integer countryCode;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "iso_alpha_2")
    public String getIsoAlpha2() {
        return isoAlpha2;
    }

    public void setIsoAlpha2(String isoAlpha2) {
        this.isoAlpha2 = isoAlpha2;
    }

    @Column(name = "iso_alpha_3")
    public String getIsoAlpha3() {
        return isoAlpha3;
    }

    public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
    }

    @Column(name = "iso_numeric")
    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
