package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.nttdata.document.BankAccount;

import reactor.core.publisher.Mono;

@EnableReactiveMongoRepositories
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String>{
	
	Mono<BankAccount> findByIdCustomerAndTypeProfileCustomer(String idCustomer, String typeProfileCustomer);

}
