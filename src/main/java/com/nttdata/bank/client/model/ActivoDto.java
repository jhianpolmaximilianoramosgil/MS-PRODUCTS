package com.nttdata.bank.client.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ActivoDto {

  @NotNull(message = "el código es requerido")
  private Integer activoId;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String tipoCliente;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String tipoCredito;

}
