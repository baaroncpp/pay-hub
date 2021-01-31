package com.payhub.mobilemoney.mtn.repository;

import com.payhub.mobilemoney.mtn.jpa.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
