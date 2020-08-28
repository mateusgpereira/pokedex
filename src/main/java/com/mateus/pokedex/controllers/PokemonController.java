package com.mateus.pokedex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.pokedex.models.Pokemon;
import com.mateus.pokedex.repositories.PokedexRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

	@Autowired
	private PokedexRepository repository;
	
	@GetMapping
	public Flux<Pokemon> getAllPokemons(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {
		return repository.findById(id)
				.map(pokemon -> ResponseEntity.ok(pokemon))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
		return repository.save(pokemon);
	}
}
