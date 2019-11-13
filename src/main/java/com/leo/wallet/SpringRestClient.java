package com.leo.wallet;

import com.leo.wallet.model.dto.WithdrawalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class SpringRestClient {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringRestClient springRestClient = new SpringRestClient();
        springRestClient.getBalanceByAccountId();
        springRestClient.withdrawal();
    }

    private void getBalanceByAccountId() {
        String GET_BALANCE_BY_PLAYER_ID = "http://localhost:8080/api/v1/wallet/getBalanceByAccountId?id=1";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(GET_BALANCE_BY_PLAYER_ID, String.class);
        System.out.println(result);
    }

    public void withdrawal() {
        String POST_WITHDRAWAL = "http://localhost:8080/api/v1/wallet/withdrawal";

        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
        withdrawalDTO.setPlayerId(1L);
        withdrawalDTO.setRequestId(1L);
        withdrawalDTO.setAmount(new BigDecimal(10));

        ResponseEntity<Map> postResponse1 = restTemplate.postForEntity(POST_WITHDRAWAL, withdrawalDTO, Map.class);
        System.out.println("postResponse1.toString() = " + postResponse1.toString());

        ResponseEntity<Map> postResponse2 = restTemplate.postForEntity(POST_WITHDRAWAL, withdrawalDTO, Map.class);
        System.out.println("postResponse2.toString() = " + postResponse2.toString());

    }

}
