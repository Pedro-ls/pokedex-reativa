package com.pedrohsilva.pokedex.controllers;

import com.pedrohsilva.pokedex.models.Pokemon;
import com.pedrohsilva.pokedex.models.PokemonEvent;
import com.pedrohsilva.pokedex.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
    private PokedexRepository repository;

    public PokemonController(PokedexRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id){
        return repository.findById(id)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon){
        return repository.save(pokemon);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value="id") String id, @RequestBody Pokemon pokemon){
        return repository.findById(id).flatMap(existingPokemon -> {
            existingPokemon.setNome(pokemon.getNome());
            existingPokemon.setCategoria(pokemon.getCategoria());
            existingPokemon.setHabilidade(pokemon.getHabilidade());
            existingPokemon.setPeso(pokemon.getPeso());
            existingPokemon.setFoto(pokemon.getFoto());
            return repository.save(existingPokemon);
        }).map(updatePokemon-> ResponseEntity.ok(updatePokemon)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePokemon(@PathVariable(value="id") String id){
        return repository.findById(id)
                .flatMap(existingPokemon ->
                        repository.delete(existingPokemon)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                        .defaultIfEmpty(ResponseEntity.notFound().build())

        );
    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemon(){
        return repository.deleteAll();
    }

    @GetMapping(value="/events", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonsEvent(){
        return Flux.interval(Duration.ofSeconds(5)).map(value ->
            new PokemonEvent(value, "pokemon ok")
        );
    }
}
