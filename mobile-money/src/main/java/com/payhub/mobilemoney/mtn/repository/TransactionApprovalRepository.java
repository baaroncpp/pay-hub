package com.payhub.mobilemoney.mtn.repository;

import com.payhub.mobilemoney.mtn.jpa.entities.TransactionApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionApprovalRepository extends JpaRepository<TransactionApproval, String> {
}
