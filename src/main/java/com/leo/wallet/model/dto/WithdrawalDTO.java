package com.leo.wallet.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WithdrawalDTO {

    @NotNull
    private Long playerId;
    @NotNull
    private Long requestId;
    @NotNull
    @Min(value = 1, message = "The amount must be grater than Zero ( amount > 0 )")
    private BigDecimal amount;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "WithdrawalDTO{" +
                "playerId=" + playerId +
                ", amount=" + amount +
                ", requestId=" + requestId +
                '}';
    }
}
