package com.zosh.service.impl;

import org.springframework.stereotype.Service;

import com.zosh.modal.Seller;
import com.zosh.modal.SellerReport;
import com.zosh.repository.SellerReportRepository;
import com.zosh.service.SellerReportService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if (sr == null) {
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updaSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }

}
