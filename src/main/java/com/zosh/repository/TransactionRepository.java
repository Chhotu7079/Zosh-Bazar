package com.zosh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findBySellerId(Long sellerId);
}
