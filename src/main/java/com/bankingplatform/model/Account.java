package com.bankingplatform.model;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal balance;

    protected Account(){

    }

    public Account(String type, BigDecimal balance){
        this.type = type;
        this.balance = balance;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public BigDecimal getBalance(){
        return balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
}
