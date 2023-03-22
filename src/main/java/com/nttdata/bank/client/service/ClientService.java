package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.Client;
import com.nttdata.bank.client.model.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

  Flux<Client> getAll();

  Mono<Client> save(ClientDto clientDto);

  Mono<Client> update(ClientDto clientDto);

  Mono<Void> delete(Integer clientId);

  Mono<Client> findById(Integer clientId);


}
