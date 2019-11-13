package com.leo.wallet.service.impl;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.CodeConstant;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.dto.CreditDTO;
import com.leo.wallet.model.dto.WithdrawalDTO;
import com.leo.wallet.model.entity.Account;
import com.leo.wallet.model.entity.Player;
import com.leo.wallet.model.entity.Transaction;
import com.leo.wallet.repository.AccountRepository;
import com.leo.wallet.repository.PlayerRepository;
import com.leo.wallet.repository.TransactionRepository;
import com.leo.wallet.service.TransactionService;
import com.leo.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlayerRepository playerRepository;


    @Autowired
    private TransactionService transactionService;


    @Override
    @Transactional(readOnly = true)
    public StandardContext<Account> getBalanceByAccountId(long id) throws WalletException {
        Account account = accountRepository.findById(id).orElse(null);
        StandardContext<Account> context = new StandardContext<>();
        if (account == null) {
            throw new WalletException(CodeConstant.DATA_NOT_FOUND, CodeConstant.DATA_NOT_FOUND_MESSAGE);
        }
        context.setErrorCode(CodeConstant.APPROVE_CODE);
        context.setData(account);
        return context;
    }

    @Override
    @Transactional(readOnly = true)
    public StandardContext<Account> getAccountByPlayerId(long id) throws WalletException {
        Player player = playerRepository.findById(id).orElse(null);
        StandardContext<Account> context = new StandardContext<>();
        if (player == null || player.getAccount() == null) {
            throw new WalletException(CodeConstant.DATA_NOT_FOUND, CodeConstant.DATA_NOT_FOUND_MESSAGE);
        }
        context.setErrorCode(CodeConstant.APPROVE_CODE);
        context.setData(player.getAccount());
        return context;
    }

    /**
     *
     * @param withdrawalDTO is a data transfer object as a input variable
     * @return StandardContext<T> is a standard generic object for passing result between layers, which include errorCode variable, errorMessage and data object variable
     * @throws WalletException
     */
    @Override
    @Transactional(rollbackFor = WalletException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public StandardContext<Account> withdrawal(WithdrawalDTO withdrawalDTO) throws WalletException {
        StandardContext<Account> ctxAccount = getAccountByPlayerId(withdrawalDTO.getPlayerId());

        if (ctxAccount.getErrorCode() == CodeConstant.APPROVE_CODE) {
            Transaction transaction = new Transaction();
            transaction.setRequestId(withdrawalDTO.getRequestId());
            transaction.setAccountId(ctxAccount.getData().getId());
            transaction.setPlayerId(withdrawalDTO.getPlayerId());
            transaction.setAmount(withdrawalDTO.getAmount());
            transaction.setBalance(ctxAccount.getData().getBalance());
            transaction.setTransactionType(false);// TransactionType: false == withdrawal and true == credit

            BigDecimal calculateBalanceAndAmount = ctxAccount.getData().getBalance().subtract(withdrawalDTO.getAmount());
            if (calculateBalanceAndAmount.compareTo(BigDecimal.ZERO) >= 0) {
                transaction.setTransactionResult(1);// TransactionResult: 1 == successful and 0 == failed
                transactionService.createTransaction(transaction);
                ctxAccount.getData().setBalance(calculateBalanceAndAmount);
                Account finalAccount = accountRepository.save(ctxAccount.getData());
                ctxAccount.setData(finalAccount);
            } else {
                transaction.setTransactionResult(0);// TransactionResult: 1 == successful and 0 == failed
                try {
                    transactionService.createTransaction(transaction);
                } catch (DataIntegrityViolationException ex) {
                    throw new WalletException(CodeConstant.REQUEST_ID_DUPLICATE, CodeConstant.REQUEST_ID_DUPLICATE_MESSAGE);
                }
                return new StandardContext<>(CodeConstant.BALANCE_NOT_ENOUGH, CodeConstant.BALANCE_NOT_ENOUGH_MESSAGE);
            }

        }
        return ctxAccount;
    }

    /**
     *
     * @param creditDTO is a data transfer object as a input variable
     * @return StandardContext<T> is a standard generic object for passing result between layers, which include errorCode variable, errorMessage and data object variable
     * @throws WalletException
     */
    @Override
    @Transactional(rollbackFor = WalletException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public StandardContext<Account> credit(CreditDTO creditDTO) throws WalletException {
        StandardContext<Account> ctxAccount = getAccountByPlayerId(creditDTO.getPlayerId());

        if (ctxAccount.getErrorCode() == CodeConstant.APPROVE_CODE) {
            Transaction transaction = new Transaction();
            transaction.setRequestId(creditDTO.getRequestId());
            transaction.setAccountId(ctxAccount.getData().getId());
            transaction.setPlayerId(creditDTO.getPlayerId());
            transaction.setAmount(creditDTO.getAmount());
            transaction.setBalance(ctxAccount.getData().getBalance());
            transaction.setTransactionType(true);// TransactionType: false == withdrawal and true == credit

            BigDecimal calculateBalanceAndAmount = ctxAccount.getData().getBalance().add(creditDTO.getAmount());
            if (calculateBalanceAndAmount.compareTo(BigDecimal.ZERO) > 0) {
                transaction.setTransactionResult(1);// TransactionResult: 1 == successful and 0 == failed
                try {
                    transactionService.createTransaction(transaction);
                } catch (DataIntegrityViolationException ex) {
                    throw new WalletException(CodeConstant.REQUEST_ID_DUPLICATE, CodeConstant.REQUEST_ID_DUPLICATE_MESSAGE);
                }
                ctxAccount.getData().setBalance(calculateBalanceAndAmount);
                Account finalAccount = accountRepository.save(ctxAccount.getData());
                ctxAccount.setData(finalAccount);
            } else {
                transaction.setTransactionResult(0);// TransactionResult: 1 == successful and 0 == failed
                try {
                    transactionService.createTransaction(transaction);
                } catch (DataIntegrityViolationException ex) {
                    throw new WalletException(CodeConstant.REQUEST_ID_DUPLICATE, CodeConstant.REQUEST_ID_DUPLICATE_MESSAGE);
                }
                return new StandardContext<>(CodeConstant.BALANCE_NOT_ENOUGH, CodeConstant.BALANCE_NOT_ENOUGH_MESSAGE);
            }
        }
        return ctxAccount;
    }


}
