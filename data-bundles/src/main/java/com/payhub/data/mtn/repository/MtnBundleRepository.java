package com.payhub.data.mtn.repository;

import com.payhub.data.mtn.constants.BundleCategory;
import com.payhub.data.mtn.entity.jpa.MtnBundle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MtnBundleRepository extends JpaRepository<MtnBundle, String> {
    Optional<MtnBundle> findByBundleName(String bundleName);
    List<MtnBundle> findAllByBundleCategory(BundleCategory bundleCategory);
}
