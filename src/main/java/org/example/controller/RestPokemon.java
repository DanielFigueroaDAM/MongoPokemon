package org.example.controller;

import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
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

    @PostMapping("/importarDesdeJson")
    public ResponseEntity<List<Pokemon>> importarDesdeJson(@RequestParam String filePath) {
        try {
            List<Pokemon> pokemons = pokemonService.importarPokedesdeFichero(filePath);
            return new ResponseEntity<>(pokemons, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insertarDirecto")
    public ResponseEntity<Pokemon> insertarDirecto(
            @RequestParam String nombre,
            @RequestParam String[] tipos,
            @RequestParam int nivel,
            @RequestParam String[] habilidades,
            @RequestParam String adestradorId) {
        Pokemon pokemon = pokemonService.insertarPokemonDirecto(nombre, tipos, nivel, habilidades, adestradorId);
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }
}


