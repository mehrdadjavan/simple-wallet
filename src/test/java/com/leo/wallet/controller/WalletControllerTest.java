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
public class WalletControllerTest {


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
     public void getBalanceByAccountId() throws WalletException {
         System.out.println("###getBalanceByAccountId#############################################");
         Map<String, Object> account = restTemplate.getForObject(getRootUrl() + "/api/v1/wallet/getBalanceByAccountId?id=1", Map.class);
         System.out.println("account.toString() = " + account.toString());
         Assert.assertEquals(CodeConstant.APPROVE_CODE, account.get("errorCode"));
         System.out.println("#####################################################################");
     }

     @Test
     public void getAccountByPlayerId() throws WalletException {
         System.out.println("###getAccountByPlayerId#############################################");
         Map<String, Object> account = restTemplate.getForObject(getRootUrl() + "/api/v1/wallet/getAccountByPlayerId?id=1", Map.class);
         System.out.println("account.toString() = " + account.toString());
         Assert.assertEquals(CodeConstant.APPROVE_CODE, account.get("errorCode"));
         System.out.println("#####################################################################");
     }

    @Test
    public void withdrawal() throws WalletException {
        System.out.println("###withdrawal#############################################");
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
        withdrawalDTO.setPlayerId(1L);
        withdrawalDTO.setRequestId(1L);
        withdrawalDTO.setAmount(new BigDecimal(10));

        ResponseEntity<Map> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/api/v1/wallet/withdrawal", withdrawalDTO, Map.class);
        System.out.println("postResponse1.toString() = " + postResponse1.toString());
        Assert.assertEquals(CodeConstant.APPROVE_CODE, postResponse1.getBody().get("errorCode"));

        ResponseEntity<Map> postResponse2 = restTemplate.postForEntity(getRootUrl() + "/api/v1/wallet/withdrawal", withdrawalDTO, Map.class);
        System.out.println("postResponse2.toString() = " + postResponse2.toString());
        Assert.assertEquals(CodeConstant.REQUEST_ID_DUPLICATE, postResponse2.getBody().get("errorCode"));

        System.out.println("#####################################################################");
    }

}
