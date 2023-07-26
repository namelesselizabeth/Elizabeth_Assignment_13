package com.coderscampus.Elizabeth_Assignment_13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.Elizabeth_Assignment_13.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
