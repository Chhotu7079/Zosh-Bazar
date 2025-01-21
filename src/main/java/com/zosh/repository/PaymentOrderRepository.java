package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.PaymentOrder;


public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long>{

    PaymentOrder findByPaymentLinkId(String paymentId);
}    
