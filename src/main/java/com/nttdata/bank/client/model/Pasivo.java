package com.nttdata.bank.client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Pasivo")
public class Pasivo {

    @Id
    private Integer pasivoId;
    private String tipoCliente; //Personal & Empresarial (P-E)
    private String tipoCuenta; //Ahorro & Corriente & PlazoFijo (A-C-P)
}
