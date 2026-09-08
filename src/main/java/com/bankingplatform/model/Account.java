package com.bankingplatform.model;

public class Account {
    private Long id;
    private String type;
    private double balance;

    public Account(Long id, String type, double balance){
        this.id = id;
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

    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
}
