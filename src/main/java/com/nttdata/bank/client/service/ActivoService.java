package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.Activo;
import com.nttdata.bank.client.model.ActivoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ActivoService {

  Flux<Activo> getAll();

  Mono<Activo> save(ActivoDto activoDto);

  Mono<Activo> update(ActivoDto activoDto);

  Mono<Void> delete(Integer activoId);

  Mono<Activo> findById(Integer activoId);


}
