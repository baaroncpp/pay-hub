package com.payhub.mobilemoney.airtel.repository;

import com.payhub.mobilemoney.airtel.jpa.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
