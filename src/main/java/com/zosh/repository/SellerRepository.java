package com.zosh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Seller;
import com.zosh.domain.AccountStatus;



public interface SellerRepository extends JpaRepository<Seller, Long>{

    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus accountStatus);
} 
