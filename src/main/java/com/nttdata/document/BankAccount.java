package com.nttdata.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bank-account")
public class BankAccount {
	
	@Id
	private String id;
	private String accountType; // cc ahorro, cc corriente, cc plazo fijo
	private String accountNumber; // nro de cuenta
	private Double amount; //saldo 
	private Boolean status;  // estado cuanta activa o inactiva
	private String idCustomer; // id cliente
	private String typeProfileCustomer; //tipo perfil cliente
	private Date createAccount; //fecha creacion de la cuenta
	
	

}
