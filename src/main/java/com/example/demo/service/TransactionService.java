package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;

@Service
public class TransactionService {
  
  private List<Transaction> transactionList;
  
  public TransactionService() {
    this.transactionList = new ArrayList<Transaction>();
  }

  public String greeting() {
    return "Good Job, Greeter";
  }
  
  public Transaction createTransaction(Transaction transaction) {
    transactionList.add(transaction);
    return getTransactionById(transaction.getTransaction_id());
  }
  
  public Object updateTransactionById(Transaction transaction, Long transaction_id) {
    Transaction trans = getTransactionById(transaction.getTransaction_id());
    trans.setAmount(transaction.getAmount());
    trans.setParent_id(transaction.getParent_id());
    trans.setType(transaction.getType());
    return getTransactionById(transaction.getTransaction_id());
  }
  
  public Transaction getTransactionById(Long transaction_id) {
    return transactionList.stream()
        .filter(x -> x.getTransaction_id() == transaction_id)
        .findFirst()
        .orElse(null);
    /**
    Returns: { "amount":double,"type":string,"parent_id":long }
     */
  }
  
  public List<Long> getTransactionByType(String type) {
    return transactionList.stream()
        .filter(x -> x.getType().equals(type))
        .map(y -> new Long(y.getTransaction_id()))
        .collect(Collectors.toCollection(ArrayList::new));
    /**
    Returns: [long, long, ... ] A json list of all transaction ids that share the same type $type.
     */
  }
  
  public double getTransactionSums(Long transaction_id) {
    return transactionList.stream()
        .filter(x -> x.getParent_id() == transaction_id)
        .map(y -> y.getAmount())
        .collect(Collectors.toCollection(ArrayList::new))
        .stream()
        .mapToDouble(d -> d.doubleValue())
        .sum();
    /**
    Returns: { "sum": double } A sum of all transactions that are transitively linked by their parent_id to $transaction_id.
     */
  }

}