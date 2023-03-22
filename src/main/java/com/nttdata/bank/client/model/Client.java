package com.nttdata.bank.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "clients")
public class Client {

  @Id
  private Integer clientId;
  private String clientName;
  private String clientType; //Personal & Empresarial (P-E)
  private String codProfile;//Personal(VIP) - Empresarial(PYME)
  private String clientDocument;
}