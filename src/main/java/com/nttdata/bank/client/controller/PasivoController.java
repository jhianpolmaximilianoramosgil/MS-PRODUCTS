package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.service.PasivoService;
import com.nttdata.bank.client.model.Pasivo;
import com.nttdata.bank.client.model.PasivoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/pasivo")
public class PasivoController {

  @Autowired
  private PasivoService pasivoService;

  @GetMapping
  public Flux<Pasivo> getAll() {
    return pasivoService
        .getAll();
  }

  @GetMapping("/{pasivoId}")
  public Mono<Pasivo> getById(@PathVariable("pasivoId") Integer pasivoId) {
    return pasivoService
        .findById(pasivoId);
  }

  @PostMapping
  public Mono<Pasivo> save(@Valid @RequestBody PasivoDto pasivoDto) {
    return pasivoService
        .save(pasivoDto);
  }

  @PostMapping("/updPasivo")
  public Mono<Pasivo> update(@Valid @RequestBody PasivoDto pasivoDto) {
    return pasivoService
        .update(pasivoDto);
  }

  @PostMapping("/delete/{pasivoId}")
  public Mono<Void> deleteByClientId(@PathVariable("pasivoId") Integer pasivoId) {
    return pasivoService
        .delete(pasivoId);
  }

}
