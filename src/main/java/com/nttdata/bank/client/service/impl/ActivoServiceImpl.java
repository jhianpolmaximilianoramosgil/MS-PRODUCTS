package com.nttdata.bank.client.service.impl;

import com.nttdata.bank.client.service.ActivoService;
import com.nttdata.bank.client.model.Activo;
import com.nttdata.bank.client.model.ActivoDto;
import com.nttdata.bank.client.repository.ActivoRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ActivoServiceImpl implements ActivoService {

    @Autowired
    private ActivoRepository activoRepository;
    @Autowired
    private Mapper mapper;
    @Override
    public Flux<Activo> getAll() {
        return activoRepository
                .findAll()
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Activo> save(ActivoDto activoDto) {
        return activoRepository
                .existsById(activoDto.getActivoId())
                .flatMap((isExist -> {
                    if (!isExist) {
                        return activoRepository.save(mapper.map(activoDto, Activo.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<Activo> update(ActivoDto activoDto) {
        return activoRepository
                .existsById(activoDto.getActivoId())
                .flatMap((isExist -> {
                    if (isExist) {
                        return activoRepository.save(mapper.map(activoDto, Activo.class));
                    } else {
                        return Mono.empty();
                    }
                }));
    }

    @Override
    public Mono<Void> delete(Integer activoId) {
        return activoRepository
                .findById(activoId)
                .flatMap(p -> activoRepository.deleteById(activoId))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Activo> findById(Integer activoId) {
        return activoRepository.
                findById(activoId);
    }
}
