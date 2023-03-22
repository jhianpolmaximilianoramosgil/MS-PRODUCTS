package com.nttdata.bank.client.repository;

import com.nttdata.bank.client.model.Activo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoRepository extends ReactiveMongoRepository<Activo, Integer> {

}
