package com.leo.wallet.repository;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = WalletException.class)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountIdOrderByCreateDateAsc(Long accountId);
}
