package com.pedrohsilva.pokedex;

import com.pedrohsilva.pokedex.models.Pokemon;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import com.pedrohsilva.pokedex.repository.PokedexRepository;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}


	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PokedexRepository repository){
		return args -> {

			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "pikachu", "eletrico", "raio", 25.4, "picachu.jpg"),
					new Pokemon(null, "Raiochu", "eletrico", "raio", 24.4, "raiochu.jpg"),
					new Pokemon(null, "Squartle", "Agua", "jato de agua", 15.4, "squartle.jpg"),
					new Pokemon(null, "chamander", "fogo", "jato de fogo", 18.4, "chamader.jpg")
			).flatMap(repository::save);

			pokedexFlux.thenMany(repository.findAll()).subscribe(System.out::println);

		};
	}

}
