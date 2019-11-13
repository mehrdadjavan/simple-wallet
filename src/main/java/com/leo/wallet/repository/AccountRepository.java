package com.leo.wallet.repository;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
@Transactional(rollbackFor = WalletException.class)
public interface AccountRepository extends JpaRepository<Account, Long> {

/*
    @Modifying
    @Query("update Account acc set acc.balance = :balance where acc.id = :id") //    update ACCOUNTS acc set acc.balance = 2 where acc.id = 1 and acc.balance-10 >=0
    int updateBalanceByAccountId(@Param("balance") BigDecimal balance, @Param("id") Long id);
*/

}
