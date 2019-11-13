package com.leo.wallet.service;

import com.leo.wallet.MyApplication;
import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.CodeConstant;
import com.leo.wallet.model.StandardContext;
import com.leo.wallet.model.dto.WithdrawalDTO;
import com.leo.wallet.model.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void getTransactionsByPlayerId() throws WalletException {
        System.out.println("####getTransactionsByPlayerId#######################");

        System.out.println("################################################");
    }

}
