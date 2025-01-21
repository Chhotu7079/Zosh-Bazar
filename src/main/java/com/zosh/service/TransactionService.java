package com.zosh.service;

import java.util.List;

import com.zosh.modal.Order;
import com.zosh.modal.Seller;
import com.zosh.modal.Transaction;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> geTransactionsBySellerId(Seller seller);
    List<Transaction> getAllTransactions();
}
