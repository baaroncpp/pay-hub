package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 14:10
 **/

@Entity
@Table(name = "t_product",schema = "core")
public class TProduct extends AuditedEntity {

    private TAccount productAccount;
    private TProductCategory productCategory;
    private String name;
    private Boolean nonActive;
    private TProvider provider;
    private String productCode;
    private String officialName;
    private Boolean hasCharge;
    private Boolean hasTariff;
    private Boolean hasSmsNotification;
    private TProvider rootProvider;


    @JoinColumn(name = "product_account",referencedColumnName = "code",insertable = false,updatable = true)
    @OneToOne(fetch = FetchType.LAZY)
    public TAccount getProductAccount() {
        return productAccount;
    }

    public void setProductAccount(TAccount productAccount) {
        this.productAccount = productAccount;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "non_active")
    public Boolean getNonActive() {
        return nonActive;
    }

    public void setNonActive(Boolean nonActive) {
        this.nonActive = nonActive;
    }

    @JoinColumn(name = "provider",referencedColumnName = "id",insertable = false,updatable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    public TProvider getProvider() {
        return provider;
    }

    public void setProvider(TProvider provider) {
        this.provider = provider;
    }

    @JoinColumn(name = "category",referencedColumnName = "id",insertable = false,updatable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    public TProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(TProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "official_name")
    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    @Column(name = "has_charge")
    public Boolean getHasCharge() {
        return hasCharge;
    }

    public void setHasCharge(Boolean hasCharge) {
        this.hasCharge = hasCharge;
    }

    @Column(name = "has_tariff")
    public Boolean getHasTariff() {
        return hasTariff;
    }

    public void setHasTariff(Boolean hasTariff) {
        this.hasTariff = hasTariff;
    }

    @Column(name = "sms_notify")
    public Boolean getHasSmsNotification() {
        return hasSmsNotification;
    }

    public void setHasSmsNotification(Boolean hasSmsNotification) {
        this.hasSmsNotification = hasSmsNotification;
    }

    @JoinColumn(name = "root_provider",referencedColumnName = "id",insertable = false,updatable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    public TProvider getRootProvider() {
        return rootProvider;
    }

    public void setRootProvider(TProvider rootProvider) {
        this.rootProvider = rootProvider;
    }
}
