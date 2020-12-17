package com.jajjamind.payvault.core.jpa.models.agent;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 02:30
 **/
@Entity
@Table(name = "t_district",schema = "core")
public class TDistrict extends BaseEntityLong {

    private TCountry country;
    private String name;
    private String region;

    @JoinColumn(name = "country_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TCountry getCountry() {
        return country;
    }

    public void setCountry(TCountry country) {
        this.country = country;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
