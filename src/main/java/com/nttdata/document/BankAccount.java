package com.nttdata.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "producto")
public class BankAccount {
	
	@Id
	private int id;
	private String accountType;
	private String accountNumber;
	private String dni;
	private double amount;
	private int edad;
	

}
