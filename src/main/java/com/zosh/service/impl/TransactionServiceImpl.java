package com.zosh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zosh.modal.Order;
import com.zosh.modal.Seller;
import com.zosh.modal.Transaction;
import com.zosh.repository.SellerRepository;
import com.zosh.repository.TransactionRepository;
import com.zosh.service.TransactionService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {

        Seller seller = sellerRepository.findById(order.getSellerId()).get();

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> geTransactionsBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
