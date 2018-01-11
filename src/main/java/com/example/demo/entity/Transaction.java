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
  private final long transaction_id = transactionCounter + 1;
  double amount;
  String type;
  long parent_id;
  
  public Transaction() {
    transactionCounter++;
  }
}
