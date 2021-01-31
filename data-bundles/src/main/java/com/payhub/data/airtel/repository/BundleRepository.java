package com.payhub.data.airtel.repository;

import com.payhub.data.airtel.entity.Bundle;
import com.payhub.data.airtel.entity.BundlePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, String> {

    Optional<Bundle> findByName(String name);
}
