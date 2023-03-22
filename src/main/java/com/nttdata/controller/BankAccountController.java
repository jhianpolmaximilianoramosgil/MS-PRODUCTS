package com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.service.BankAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.nttdata.document.BankAccount;

@RestController
@RequestMapping("/BankAccount")
@CrossOrigin("*")
public class BankAccountController {
	
	@Autowired
    BankAccountService bankAccoService;
	
	@PostMapping(value = "/saveBankAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<BankAccount> saveBankAccount(@RequestBody BankAccount bAccount){
		
		return bAccount.getAccountType().equals("ahorros")
                ?  bankAccoService.saveSavingAccount(bAccount)
            : bAccount.getAccountType().equals("cuenta corriente")
                ? bankAccoService.saveCurrentAccount(bAccount)
                : bankAccoService.saveFixedTerm(bAccount);
	
	}
	
	@GetMapping(value = "/findBankAccountById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<BankAccount> getBankAccountById(@PathVariable("id") String id){
		
		System.out.println("Buscar cta. bancaria");
        return bankAccoService.findById(id);
		
	}
	
	@GetMapping(value = "/findAllBankAccount", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<BankAccount> getAllBankAccount(){
		
		System.out.println("Listar cta. bancarias");
        return bankAccoService.findAll();
		
	}
	
	@PutMapping(value = "/updateBankAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<BankAccount> updateBankAccount(@RequestBody BankAccount bAccount){
		
		System.out.println("Actualizar cta. bancaria");
        return bankAccoService.update(bAccount);
	
	}
	
	
	@DeleteMapping(value = "/deleteBankAccountById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<BankAccount> deleteBankAccountById(@PathVariable("id") String id){
		
		System.out.println("Eliminar cta. bancaria");
        return bankAccoService.deleteById(id);
		
	}
	
	/*@GetMapping(value = "findAccountBankTypeCustomer/{customer}/{type}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public  Mono<BankAccount> findAccountBankTypeCustomer(@PathVariable String type, @PathVariable String customer){
        System.out.println("Buscar cta. bancaria por tipo y cliente");
        return bankAccoService.findByTypeAndCustomer(type, customer);
    }*/
	

}
