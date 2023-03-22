package com.nttdata.bank.client.repository;

import com.nttdata.bank.client.model.Pasivo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasivoRepository extends ReactiveMongoRepository<Pasivo, Integer> {

}
