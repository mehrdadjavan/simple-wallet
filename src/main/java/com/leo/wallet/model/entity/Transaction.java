package com.leo.wallet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "transactions")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", unique = true, nullable = false)
    private Long requestId;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false) // false == withdrawal and true == credit
    @JsonIgnore
    private boolean transactionType;

    @Persistent
    private String transactionTypeInfo;


    @Column(name = "transaction_result", nullable = false)
    private int transactionResult;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    public Transaction() {
    }

    public Transaction(Long requestId, Long playerId, Long accountId, BigDecimal balance, BigDecimal amount, boolean transactionType, int transactionResult) {
        this.requestId = requestId;
        this.playerId = playerId;
        this.accountId = accountId;
        this.balance = balance;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionResult = transactionResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isTransactionType() {
        return transactionType;
    }

    public void setTransactionType(boolean transactionType) {
        this.transactionType = transactionType;
        //todo: change to Enum in future
        if (transactionType)
            transactionTypeInfo = "credit";
        else
            transactionTypeInfo = "withdrawal";
    }

    public int getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(int transactionResult) {
        this.transactionResult = transactionResult;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTransactionTypeInfo() {
        return transactionTypeInfo;
    }

}