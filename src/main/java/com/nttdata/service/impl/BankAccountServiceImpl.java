package com.nttdata.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nttdata.config.WebClientConfig;
import com.nttdata.document.BankAccount;
import com.nttdata.model.Customers;
import com.nttdata.repository.BankAccountRepository;
import com.nttdata.service.BankAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BankAccountService{
	
	@Autowired
	BankAccountRepository bankAccountRepository;
	private final WebClientConfig webClientConfig = new WebClientConfig();

	@Override
	public Mono<BankAccount> findById(String id) {
		return bankAccountRepository.findById(id);
	}

	@Override
	public Flux<BankAccount> findAll() {
		return bankAccountRepository.findAll();
	}

	@Override
	public Mono<BankAccount> save(BankAccount bAccount) {
		bAccount.setStatus(true);
		bAccount.setCreateAccount(new Date());
        return bankAccountRepository.save(bAccount);
	}

	@Override
	public Mono<BankAccount> update(BankAccount bAccount) {
		return  bankAccountRepository.findById(bAccount.getId())
                .switchIfEmpty(Mono.empty())
                .flatMap(baO -> {
                	baO.setAccountType(bAccount.getAccountType());
                	baO.setAccountNumber(bAccount.getAccountNumber());
                	baO.setAmount(bAccount.getAmount());
                	baO.setIdCustomer(bAccount.getIdCustomer());
                	baO.setTypeProfileCustomer(bAccount.getTypeProfileCustomer());
                	return bankAccountRepository.save(baO);
                });
	}

	@Override
	public Mono<BankAccount> deleteById(String id) {
		return bankAccountRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(baO -> {
                	baO.setStatus(false);
                    return bankAccountRepository.save(baO);
                });
	}

	@Override
	public Mono<BankAccount> findByIdCustomerAndTypeProfileCustomer(String customer, String type) {
		return bankAccountRepository.findByIdCustomerAndTypeProfileCustomer(customer, type);
	}
	
	@Override
    public Mono<BankAccount> saveSavingAccount(BankAccount entity) {
        //guardar cuenta de ahorros
        System.out.println("guarda ahorros");
        Mono<Customers> customer = webClientConfig.getCustomerById(entity.getIdCustomer());
        Mono<BankAccount>personalb001 = bankAccountRepository.findByIdCustomerAndTypeProfileCustomer(entity.getIdCustomer(), "personal1");


        return customer
                .filter(c -> c.getCodProfile().equals("vip1") || c.getCodProfile().equals("personal1"))
                .flatMap(cc -> {
                    entity.setTypeProfileCustomer(cc.getCodProfile());
                            return cc.getCodProfile().equals("personal1")
                                    ? personalb001.switchIfEmpty(save(entity))
                                    : Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT));

                        }
                );
    }


    @Override
    public Mono<BankAccount> saveCurrentAccount(BankAccount entity) {
        //guardar cuenta corriente
        System.out.println("guarda corriente");
        Mono<Customers> customer = webClientConfig.getCustomerById(entity.getIdCustomer());
        Mono<BankAccount> personalb001 = bankAccountRepository.findByIdCustomerAndTypeProfileCustomer(entity.getIdCustomer(), "personal1");
        Mono<BankAccount> vip001 = bankAccountRepository.findByIdCustomerAndTypeProfileCustomer(entity.getIdCustomer(), "vip1");

        return customer.flatMap(c ->{
                            entity.setTypeProfileCustomer(c.getCodProfile());
                            return c.getCodProfile() == "personal1" ? personalb001.switchIfEmpty(save(entity))
                                    : c.getCodProfile() == "vip1" ? vip001.switchIfEmpty(save(entity))
                                    : c.getCodProfile() == "empresarial1" ? save(entity)
                                    : Mono.empty();
                        }

                );
    }

    @Override
    public Mono<BankAccount> saveFixedTerm(BankAccount entity) {
        //guardar cuenta plazo fijo
        System.out.println("guarda plazo fijo");
        Mono<Customers> customer = webClientConfig.getCustomerById(entity.getIdCustomer());
        return customer.filter(c -> c.getCodProfile().equals("vip1") || c.getCodProfile().equals("personal1"))
                .flatMap(cu-> {
                    System.out.println("Se entró al flatmap:" + cu.getCodProfile());
                    return save(entity);
                });
    }

}
