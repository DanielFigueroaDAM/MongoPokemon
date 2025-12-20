package org.example.controller;

import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.service.AdestradorService;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(RestPokemon.MAPPING)
public class RestPokemon {
    public static final String MAPPING = "/pokemons";
    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/guardar")
    public ResponseEntity<Pokemon> guardar(@RequestBody Pokemon pokemon) {
        pokemonService.crearPokemon(pokemon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Pokemon>> listarTodosPokemons() {
        List <Pokemon> pokemons = pokemonService.buscarPokemons();
        return new ResponseEntity<>(pokemons,HttpStatus.OK);
    }
    @GetMapping("/getAdestradorDePokemon/{id}")
    public ResponseEntity<Adestrador> getAdestradorDePokemon(@PathVariable String id) {
        Adestrador p = pokemonService.buscarAdestradorDepokemon(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }
}
