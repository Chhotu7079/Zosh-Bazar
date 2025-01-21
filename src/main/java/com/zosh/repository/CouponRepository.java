package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

    Coupon findByCode(String code);
}
