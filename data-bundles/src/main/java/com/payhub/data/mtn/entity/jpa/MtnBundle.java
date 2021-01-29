package com.payhub.data.mtn.entity;

import com.payhub.data.mtn.constants.BundleCategory;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "mtn_bundle", schema = "mtn_bundles")
public class MtnBundle {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "bundle_name", unique = true, nullable = false)
    private String bundleName;//MTN BUNDLE ID
    private BundleCategory category;
    private String description;
}
