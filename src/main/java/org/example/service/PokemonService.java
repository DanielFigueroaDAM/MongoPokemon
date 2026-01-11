package org.example.service;

import org.example.model.Adestrador;
import org.example.model.Pokemon;
import org.example.repository.AdestradorRepository;
import org.example.repository.PokemonRepository;
import org.example.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepo;
    private final AdestradorRepository adestradorRepo;
    private final JsonUtils jsonUtils;

    public PokemonService(PokemonRepository pokemonRepo, AdestradorRepository adestradorRepo, JsonUtils jsonUtils) {
        this.pokemonRepo = pokemonRepo;
        this.adestradorRepo = adestradorRepo;
        this.jsonUtils = jsonUtils;
    }

    public void crearPokemon(Pokemon a) {
        pokemonRepo.save(a);
    }

    public Pokemon buscarPokemon(String id) {
        return pokemonRepo.findById(id).orElse(null);
    }

    public List<Pokemon> buscarPokemons() {
        return pokemonRepo.findAll();
    }

    public Adestrador buscarAdestradorDepokemon(String idPokemon) {
        Pokemon pokemon = buscarPokemon(idPokemon);
        if (pokemon == null) return null;

        Adestrador adestrador = adestradorRepo.findById(pokemon.getAdestradorId()).orElse(null);

        return adestrador;
    }

    /**
     * Importa pokemons desde un archivo JSON
     *
     * @param filePath Ruta del archivo JSON
     * @return Lista de pokemons importados
     * @throws IOException Si hay error al leer el archivo
     */
    public List<Pokemon> importarPokedesdeFichero(String filePath) throws IOException {
        List<Pokemon> pokemons = jsonUtils.readJsonFile(filePath, Pokemon.class);
        pokemons.forEach(this::crearPokemon);
        return pokemons;
    }

    /**
     * Inserta un pokemon directamente desde los datos proporcionados
     *
     * @param nombre      Nombre del pokemon
     * @param tipos       Array de tipos del pokemon
     * @param nivel       Nivel del pokemon
     * @param habilidades Array de habilidades del pokemon
     * @param adestradorId ID del adestrador propietario
     * @return El pokemon creado
     */
    public Pokemon insertarPokemonDirecto(String nombre, String[] tipos, int nivel, String[] habilidades, String adestradorId) {
        Pokemon p = new Pokemon();
        p.setNome(nombre);
        p.setTipos(tipos);
        p.setNivel(nivel);
        p.setHabilidades(habilidades);
        p.setAdestradorId(adestradorId);
        crearPokemon(p);
        return p;
    }
}
