package com.nttdata.bank.client.service.impl;

import com.nttdata.bank.client.service.PasivoService;
import com.nttdata.bank.client.model.Pasivo;
import com.nttdata.bank.client.model.PasivoDto;
import com.nttdata.bank.client.repository.PasivoRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PasivoServiceImpl implements PasivoService {

    @Autowired
    private PasivoRepository pasivoRepository;
    @Autowired
    private Mapper mapper;
    @Override
    public Flux<Pasivo> getAll() {
        return pasivoRepository
                .findAll()
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Pasivo> save(PasivoDto pasivoDto) {
        return pasivoRepository
                .existsById(pasivoDto.getPasivoId())
                .flatMap((isExist -> {
                    if (!isExist) {
                        return pasivoRepository.save(mapper.map(pasivoDto, Pasivo.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<Pasivo> update(PasivoDto pasivoDto) {
        return pasivoRepository
                .existsById(pasivoDto.getPasivoId())
                .flatMap((isExist -> {
                    if (isExist) {
                        return pasivoRepository.save(mapper.map(pasivoDto, Pasivo.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<Void> delete(Integer pasivoId) {
        return pasivoRepository
                .findById(pasivoId)
                .flatMap(p -> pasivoRepository.deleteById(pasivoId))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Pasivo> findById(Integer pasivoId) {
        return pasivoRepository.
                findById(pasivoId);
    }
}
