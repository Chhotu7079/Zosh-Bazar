package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.SellerReport;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long>{
    SellerReport findBySellerId(Long sellerId);
}
