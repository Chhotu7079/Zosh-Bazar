package com.zosh.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.modal.Seller;
import com.zosh.modal.Transaction;
import com.zosh.service.SellerService;
import com.zosh.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;


    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
                @RequestHeader("Authorization") String jwt) throws Exception{

        Seller seller = sellerService.getSellerProfile(jwt);
        List<Transaction> transactions = transactionService.geTransactionsBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

}
