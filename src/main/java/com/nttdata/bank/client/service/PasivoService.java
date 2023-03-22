package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.Pasivo;
import com.nttdata.bank.client.model.PasivoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PasivoService {

  Flux<Pasivo> getAll();

  Mono<Pasivo> save(PasivoDto pasivoDto);

  Mono<Pasivo> update(PasivoDto pasivoDto);

  Mono<Void> delete(Integer pasivoId);

  Mono<Pasivo> findById(Integer pasivoId);


}
