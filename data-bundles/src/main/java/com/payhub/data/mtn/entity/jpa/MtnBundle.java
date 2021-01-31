package com.payhub.data.mtn.entity.jpa;

import com.payhub.data.mtn.constants.BundleCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_mtn_bundle", schema = "mtn_bundles")
public class MtnBundle {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "bundle_name", unique = true, nullable = false)
    private String bundleName;//MTN BUNDLE ID

    @Column(name = "category", nullable = false)
    private BundleCategory category;

    @Column(name = "description", nullable = false)
    private String description;

    public MtnBundle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public BundleCategory getCategory() {
        return category;
    }

    public void setCategory(BundleCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
