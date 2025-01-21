package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long id);
    
} 
