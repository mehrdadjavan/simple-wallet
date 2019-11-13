package com.leo.wallet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    public Account() {

    }

    public Account(String accountName, BigDecimal balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public Account(long id,BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
