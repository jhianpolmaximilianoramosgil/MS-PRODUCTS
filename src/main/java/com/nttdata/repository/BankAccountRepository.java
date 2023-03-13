package com.nttdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nttdata.document.BankAccount;

public interface BankAccountRepository extends MongoRepository<BankAccount, Integer>{

}
