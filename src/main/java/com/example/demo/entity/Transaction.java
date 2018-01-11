package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private static long transactionCounter = 0;
  @JsonIgnore
  @Setter(AccessLevel.NONE)
  private final long transaction_id = transactionCounter;
  private double amount;
  private String type;
  private long parent_id;
  
  public Transaction() {
    transactionCounter++;
  }
}
