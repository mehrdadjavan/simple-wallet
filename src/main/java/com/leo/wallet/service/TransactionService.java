package com.leo.wallet.service;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    StandardContext<List<Transaction>> getTransactionsByPlayerId(long id) throws WalletException;
    StandardContext<Transaction> createTransaction(Transaction transaction) throws WalletException;

}
