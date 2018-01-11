package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping(value = "/transactionservice/")
public class TransactionController {
  
  @Autowired
  TransactionService service;
  
  @RequestMapping(method = RequestMethod.POST, value = "/transaction/")
  public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
    return new ResponseEntity<>(service.createTransaction(transaction), HttpStatus.OK);
  }
  
  @RequestMapping(method = RequestMethod.PUT, value = "/transaction/{transaction_id}")
  public ResponseEntity<?> updateTransactionById(@RequestBody Transaction transaction, @PathVariable Long transaction_id) {
    return new ResponseEntity<>(service.updateTransactionById(transaction, transaction_id), HttpStatus.OK);
    /**
    PUT /transactionservice/transaction/$transaction_id
    Body: { "amount":double,"type":string,"parent_id":long }
    where:
      • transaction id is a long specifying a new transaction
      • amount is a double specifying the amount
      • type is a string specifying a type of the transaction.
      • parent id is an optional long that may specify the parent transaction of this transaction.
    **/
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/transaction/{transaction_id}")
  public ResponseEntity<?> getTransactionById(@PathVariable Long transaction_id) {
    return new ResponseEntity<>(service.getTransactionById(transaction_id), HttpStatus.OK);
    /**
    GET /transactionservice/transaction/$transaction_id
      Returns: { "amount":double,"type":string,"parent_id":long }
     */
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/types/{type}")
  public ResponseEntity<?> getTransactionByType(@PathVariable String type) {
    return new ResponseEntity<>(service.getTransactionByType(type), HttpStatus.OK);
    /**
    GET /transactionservice/types/$type
      Returns: [long, long, ... ] A json list of all transaction ids that share the same type $type.
     */
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/sum/{transaction_id}")
  public ResponseEntity<?> getTransactionSums(@PathVariable Long transaction_id) {
    return new ResponseEntity<>(service.getTransactionSums(transaction_id), HttpStatus.OK);
    /**
    GET /transactionservice/sum/$transaction_id
      Returns: { "sum": double } A sum of all transactions that are transitively linked by their parent_id to $transaction_id.
     */
  }
  
}
