package org.example.service;

import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.repository.AdestradorRepository;
import org.example.repository.PokemonRepository;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepo;
    private final AdestradorRepository adestradorRepo;

    public PokemonService(PokemonRepository pokemonRepo, AdestradorRepository adestradorRepo) {
        this.pokemonRepo = pokemonRepo;
        this.adestradorRepo = adestradorRepo;
    }

    public void crearPokemon(Pokemon a) {
        pokemonRepo.save(a);
    }

    public Pokemon buscarPokemon(String id) {
        return pokemonRepo.findById(id).orElse(null);
    }
    // Buscar pokemon
    public List<Pokemon> buscarPokemons() {
        return pokemonRepo.findAll();
    }

    // Buscar adestradorDepokemon
    public Adestrador buscarAdestradorDepokemon(String idPokemon) {
        Pokemon pokemon = buscarPokemon(idPokemon);
        if (pokemon == null) return null;

        Adestrador adestrador = adestradorRepo.findById(pokemon.getAdestradorId()).orElse(null);

        return adestrador;
    }
}
