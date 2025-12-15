package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "adestradores   ")
public class Adestrador {
    @Id
    private String id_adestrador;
    private String nome;
    private int idade;
    private String cidade;

    public String getId_adestrador() {
        return id_adestrador;
    }

    public void setId_adestrador(String id_adestrador) {
        this.id_adestrador = id_adestrador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
