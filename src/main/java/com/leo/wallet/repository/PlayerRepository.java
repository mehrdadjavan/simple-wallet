package com.leo.wallet.repository;

import com.leo.wallet.exception.WalletException;
import com.leo.wallet.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = WalletException.class)
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
