package com.nttdata.service;

import com.nttdata.document.BankAccount;

import reactor.core.publisher.Mono;

public interface BankAccountService extends GeneralOperationService<BankAccount, String>{
	
	//Guardar Cuenta Ahorros
    Mono<BankAccount> saveSavingAccount(BankAccount entity);
    //Guardar Cuenta Corriente
    Mono<BankAccount> saveCurrentAccount(BankAccount entity);
    //Guardar Cuenta Plazo Fijo
    Mono<BankAccount> saveFixedTerm(BankAccount entity);
	
	Mono<BankAccount> findByIdCustomerAndTypeProfileCustomer(String type, String customer);

}
