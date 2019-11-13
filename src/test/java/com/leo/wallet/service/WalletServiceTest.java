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
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @Test
    public void getBalanceByAccountId() throws WalletException {
        System.out.println("####getBalanceByAccountId#######################");
        long accountId = 2;
        StandardContext<Account> context = walletService.getBalanceByAccountId(accountId);
        System.out.println("context = " + context.getData().toString());
        Assert.assertEquals(CodeConstant.APPROVE_CODE, context.getErrorCode());
        System.out.println("################################################");
    }

    @Test
    public void  withdrawal() throws WalletException {
        System.out.println("####withdrawal#######################");
        WithdrawalDTO withdrawalDTO=new WithdrawalDTO();
        withdrawalDTO.setRequestId(123L);
        withdrawalDTO.setAmount(new BigDecimal(10));
        withdrawalDTO.setPlayerId(2L);
        StandardContext<Account> context = walletService.withdrawal(withdrawalDTO);
        System.out.println("context = " + context.getData().toString());
        Assert.assertEquals(CodeConstant.APPROVE_CODE, context.getErrorCode());
        System.out.println("################################################");
    }


}
