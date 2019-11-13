package com.leo.wallet.controller;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.entity.Transaction;
import com.leo.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TransactionController is a RestController that will be using to get transactions
 *
 * @author Mehrdad Javan
 *
 */
@RestController
@RequestMapping("/api/v1/transaction")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/getTransactionsByPlayerId", method = RequestMethod.GET)
    public StandardContext<List<Transaction>> getTransactionsByPlayerId(@RequestParam(value = "id") @NotNull Long playerId) throws WalletException {
        return transactionService.getTransactionsByPlayerId(playerId);
    }


}
