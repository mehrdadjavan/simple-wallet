package com.leo.wallet.controller;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.dto.CreditDTO;
import com.leo.wallet.model.dto.WithdrawalDTO;
import com.leo.wallet.model.entity.Account;
import com.leo.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * WalletController is a RestController that will be using to get account, balance, withdrawal, and credit
 *
 * @author Mehrdad Javan
 *
 */
@RestController
@RequestMapping("/api/v1/wallet")
@Validated
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "### wallet service is available ###";
    }

    @RequestMapping(value = "/getBalanceByAccountId", method = RequestMethod.GET)
    public StandardContext<Account> getBalanceByAccountId(@RequestParam(value = "id") @NotNull Long accountId) throws WalletException {
        return walletService.getBalanceByAccountId(accountId);
    }

    @RequestMapping(value = "/getAccountByPlayerId", method = RequestMethod.GET)
    public StandardContext<Account> getAccountByPlayerId(@RequestParam(value = "id") @NotNull Long playerId) throws WalletException {
        return walletService.getAccountByPlayerId(playerId);
    }


    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public StandardContext<Account> withdrawal(@Valid @RequestBody WithdrawalDTO withdrawalDTO) throws WalletException {
        return walletService.withdrawal(withdrawalDTO);
    }

    @RequestMapping(value = "/credit", method = RequestMethod.POST)
    public StandardContext<Account> credit(@Valid @RequestBody CreditDTO creditDTO) throws WalletException {
        return walletService.credit(creditDTO);
    }

}