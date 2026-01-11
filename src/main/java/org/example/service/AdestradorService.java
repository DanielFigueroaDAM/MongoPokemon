package org.example.service;

import org.example.model.Adestrador;
import org.example.repository.AdestradorRepository;
import org.example.utils.JsonUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class AdestradorService {

    private final AdestradorRepository adestradorRepo;
    private final JsonUtils jsonUtils;

    public AdestradorService(AdestradorRepository adestradorRepo, JsonUtils jsonUtils) {
        this.adestradorRepo = adestradorRepo;
        this.jsonUtils = jsonUtils;
    }

    public void crearAdestrador(Adestrador a) {
        adestradorRepo.save(a);
    }

    public Adestrador buscarAdestrador(String id) {
        return adestradorRepo.findById(id).orElse(null);
    }

    public List<Adestrador> buscarAdestradores() {
        return adestradorRepo.findAll();
    }

    /**
     * Importa adestradores desde un archivo JSON
     *
     * @param filePath Ruta del archivo JSON
     * @return Lista de adestradores importados
     * @throws IOException Si hay error al leer el archivo
     */
    public List<Adestrador> importarAdestradoresdesdeFichero(String filePath) throws IOException {
        // A partir del jsonUtils lee el archivo y lo convierte en una lista de adestradores
        List<Adestrador> adestradores = jsonUtils.readJsonFile(filePath, Adestrador.class);
        // El forEach itera sobre la lista
        adestradores.forEach(this::crearAdestrador); // llama a la función propia para guardar cada adestrador
        return adestradores;
    }

    /**
     * Inserta un adestrador directamente desde los datos proporcionados
     *
     * @param nombre Nombre del adestrador
     * @param idade  Edad del adestrador
     * @param cidade Ciudad del adestrador
     * @return El adestrador creado
     */
    public Adestrador insertarAdestradorDirecto(String nombre, int idade, String cidade) {
        Adestrador a = new Adestrador();
        a.setNome(nombre);
        a.setIdade(idade);
        a.setCidade(cidade);
        crearAdestrador(a);
        return a;
    }
}
