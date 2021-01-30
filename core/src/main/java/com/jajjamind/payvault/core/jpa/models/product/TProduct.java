package com.jajjamind.payvault.core.jpa.models.product;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.enums.ProductCategoryEnum;

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
    private String name;
    private Boolean nonActive;
    private String provider;
    private ProductCategoryEnum productCategory;
    private String productCode;
    private String officialName;
    private Boolean hasCharge;
    private Boolean hasTariff;
    private Boolean hasSmsNotification;


    @JoinColumn(name = "product_code",referencedColumnName = "code",insertable = false,updatable = false)
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

    @Column(name = "provider")
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Column(name = "category")
    public ProductCategoryEnum getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryEnum productCategory) {
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
}
