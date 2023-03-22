package com.nttdata.bank.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Activo")
public class Activo {

    @Id
    private Integer activoId;
    private String tipoCliente; //Personal & Empresarial (P-E)
    private String tipoCredito; //Personal & Empresarial & Tarjeta de Credito Empresarial (P-E-TC(P-E))
}
