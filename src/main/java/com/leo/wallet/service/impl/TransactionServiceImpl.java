package com.leo.wallet.service.impl;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.CodeConstant;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.entity.Account;
import com.leo.wallet.model.entity.Player;
import com.leo.wallet.model.entity.Transaction;
import com.leo.wallet.repository.AccountRepository;
import com.leo.wallet.repository.PlayerRepository;
import com.leo.wallet.repository.TransactionRepository;
import com.leo.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public StandardContext<List<Transaction>> getTransactionsByPlayerId(long id) throws WalletException {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new WalletException(CodeConstant.DATA_NOT_FOUND, CodeConstant.DATA_NOT_FOUND_MESSAGE);
        }
        List<Transaction> transactions = transactionRepository.findAllByAccountIdOrderByCreateDateAsc(player.getAccount().getId());
        StandardContext<List<Transaction>> transactionContext = new StandardContext<>();
        if (transactions.size() != 0) {
            transactionContext.setErrorCode(CodeConstant.APPROVE_CODE);
            transactionContext.setData(transactions);
        } else {
            transactionContext.setErrorCode(CodeConstant.DATA_NOT_FOUND);
            transactionContext.setErrorMessage(CodeConstant.DATA_NOT_FOUND_MESSAGE);
        }
        return transactionContext;
    }

    @Override
    @Transactional(rollbackFor = WalletException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public StandardContext<Transaction> createTransaction(Transaction transaction) throws WalletException {
        StandardContext<Transaction> context = new StandardContext<>();

        Player player = playerRepository.findById(transaction.getPlayerId()).orElse(null);
        if (player == null || player.getAccount() == null) {
            throw new WalletException(CodeConstant.DATA_NOT_FOUND, CodeConstant.DATA_NOT_FOUND_MESSAGE);
        }
        try {
            Transaction transactionResult = transactionRepository.save(transaction);
            context.setErrorCode(CodeConstant.APPROVE_CODE);
            context.setData(transactionResult);
            return context;
        } catch (DataIntegrityViolationException ex) {
            throw new WalletException(CodeConstant.REQUEST_ID_DUPLICATE, CodeConstant.REQUEST_ID_DUPLICATE_MESSAGE);
        }
    }
}
