package org.example.service;

import org.example.model.Adestrador;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pokemon")
public class PokemonService {
    @Id
    private String id;

    private String nome;
    private String[] tipos;

    private int nivel;

    private String[] habilidades;


}
