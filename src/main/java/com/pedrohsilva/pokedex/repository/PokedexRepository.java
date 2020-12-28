package com.pedrohsilva.pokedex.repository;

import com.pedrohsilva.pokedex.models.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository <Pokemon, String> {}