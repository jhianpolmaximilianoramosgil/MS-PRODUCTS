package com.nttdata.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneralOperationService<T, ID> {
	
	Mono<T> findById(ID id);
	Flux<T> findAll();
    Mono<T> save(T entity);
    Mono<T> update(T entity);
    Mono<T> deleteById(ID id);

}
