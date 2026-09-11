package com.bankingplatform.dto;

import java.math.BigDecimal;

public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;

    public TransferRequest(){}

    public TransferRequest(Long fromAccountId, Long toAccountId, BigDecimal amount){
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public Long getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(Long toAccountId){
        this.toAccountId = toAccountId;
    }
    public Long getFromAccountId(){
        return fromAccountId;
    }
    public void setFromAccountId(Long fromAccountId){
        this.fromAccountId = fromAccountId;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

}
