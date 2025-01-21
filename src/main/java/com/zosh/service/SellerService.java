package com.zosh.service;

import java.util.List;

import com.zosh.domain.AccountStatus;
import com.zosh.exception.SellerException;
import com.zosh.modal.Seller;

public interface SellerService {

    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSellers(AccountStatus status);
    Seller updatedSeller(Long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller VerifyEmail(String email, String otp) throws Exception;
    Seller updatSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;
}
