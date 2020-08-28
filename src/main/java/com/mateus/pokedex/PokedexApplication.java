package com.mateus.pokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.mateus.pokedex.models.Pokemon;
import com.mateus.pokedex.repositories.PokedexRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init (ReactiveMongoOperations operations,
			PokedexRepository repository) {
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Bulbasaur", "grass", "razor leaf", 6.09),
					new Pokemon(null, "Charizard", "fire", "fire blast", 90.05),
					new Pokemon(null, "Squirtle", "water", "hidro pump", 6.50),
					new Pokemon(null, "Kadabra", "psychic", "psybeam", 5.60))
					.flatMap(repository::save);
			
			pokedexFlux.thenMany(repository.findAll())
				.subscribe(System.out::println);
		};
	}

}
