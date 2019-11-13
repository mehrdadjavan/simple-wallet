package com.leo.wallet.controller;

import com.leo.wallet.MyApplication;
import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.CodeConstant;
import com.leo.wallet.model.dto.WithdrawalDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }
     @Test
     public void getTransactionsByPlayerId() throws WalletException {
         System.out.println("###getTransactionsByPlayerId#############################################");
         Map<String, Object> account = restTemplate.getForObject(getRootUrl() + "/api/v1/transaction/getTransactionsByPlayerId?id=1", Map.class);
         System.out.println("account.toString() = " + account.toString());
         Assert.assertNotNull(account.get("errorCode"));
         System.out.println("#####################################################################");
     }

}
