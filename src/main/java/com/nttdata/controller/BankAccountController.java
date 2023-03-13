package com.nttdata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.document.BankAccount;
import com.nttdata.repository.BankAccountRepository;

@RestController
@RequestMapping("/BankAccount")
@CrossOrigin("*")
public class BankAccountController {
	
	@Autowired
	private BankAccountRepository bAccountRepo;
	
	@PostMapping(value = "/saveBankAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveBankAccount(@RequestBody BankAccount bAccount){
		
		try {
			
			BankAccount bAccoSave = bAccountRepo.save(bAccount);
			return new ResponseEntity<BankAccount>(bAccoSave, HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@GetMapping(value = "findBankAccountById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBankAccountById(@PathVariable("id") int id){
		
		try {
			Optional<BankAccount> bAcco = bAccountRepo.findById(id);
			return new ResponseEntity<BankAccount>(bAcco.get(), HttpStatus.FOUND); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "findAllBankAccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBankAccount(){
		
		try {
			List<BankAccount> bAcco = bAccountRepo.findAll();
			return new ResponseEntity<List<BankAccount>>(bAcco, HttpStatus.FOUND); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value = "/updateBankAccount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateBankAccount(@RequestBody BankAccount bAccount){
		
		try {
			
			BankAccount bAccoUpd = bAccountRepo.save(bAccount);
			return new ResponseEntity<BankAccount>(bAccoUpd, HttpStatus.ACCEPTED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@DeleteMapping(value = "deleteBankAccountById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteBankAccountById(@PathVariable("id") int id){
		
		try {
			bAccountRepo.deleteById(id);
			return new ResponseEntity<String>("Se elimino la colección", HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
