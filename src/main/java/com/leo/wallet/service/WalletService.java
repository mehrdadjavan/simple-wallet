package com.leo.wallet.service;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.dto.CreditDTO;
import com.leo.wallet.model.dto.WithdrawalDTO;
import com.leo.wallet.model.entity.Account;
import com.leo.wallet.model.entity.Transaction;

import java.util.List;

public interface WalletService {

    StandardContext<Account> getBalanceByAccountId(long id) throws WalletException;
    StandardContext<Account> getAccountByPlayerId(long id) throws WalletException;
    StandardContext<Account> withdrawal(WithdrawalDTO withdrawalDTO) throws WalletException;
    StandardContext<Account> credit(CreditDTO creditDTO) throws WalletException;

}
