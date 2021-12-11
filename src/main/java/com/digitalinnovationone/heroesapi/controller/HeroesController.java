package com.digitalinnovationone.heroesapi.controller;

import static com.digitalinnovationone.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import com.digitalinnovationone.heroesapi.document.Heroes;
import com.digitalinnovationone.heroesapi.service.HeroesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {

    @Autowired
    HeroesService heroesService;     

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems() {
        log.info("Requesting the list of all Heroes");
        return heroesService.findAll();
    }
    
    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public ResponseEntity<Mono<Heroes>> findByIdHero(@PathVariable String id) {
        log.info("Requesting the Hero with id {}", id);
        return heroesService.findByIdHero(id);
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteByIdHero(@PathVariable String id) {
        log.info("Deleting the Hero with id {}", id);
        return heroesService.deleteByIdHero(id);  
    }

}
