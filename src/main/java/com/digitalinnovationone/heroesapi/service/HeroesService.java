package com.digitalinnovationone.heroesapi.service;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.repository.HeroesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class HeroesService {

    @Autowired
    private HeroesRepository heroesRepository;
   
    public Flux<Heroes> findAll() {
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public ResponseEntity<Mono<Heroes>> findByIdHero(String id) {        
        Optional<Heroes> optional = this.heroesRepository.findById(id);
        return optional.map(heroes -> ResponseEntity.ok().body(Mono.justOrEmpty(heroes)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Mono<Heroes> save(Heroes heroes) {
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<HttpStatus> deleteByIdHero(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
