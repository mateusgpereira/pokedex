package com.mateus.pokedex.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mateus.pokedex.models.Pokemon;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String>{

}
