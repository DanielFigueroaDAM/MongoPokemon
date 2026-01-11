package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonUtils {
    // El ObjectMapper es el encargado de las conversiones JSON
    // Se instancia una sola vez para reutilizarla
    private final ObjectMapper objectMapper;

    public JsonUtils() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Lee un archivo JSON y lo convierte a una lista de objetos del tipo especificado
     *
     * @param filePath Ruta del archivo JSON
     * @param clazz    Clase del tipo de objeto a deserializar
     * @return Lista de objetos del tipo especificado
     * @throws IOException Si hay error al leer el archivo
     */
    public <T> List<T> readJsonFile(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
        );
    }

    /**
     * Convierte un objeto a JSON
     *
     * @param object Objeto a convertir
     * @return String en formato JSON
     * @throws IOException Si hay error en la conversión
     */
    public String objectToJson(Object object) throws IOException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    /**
     * Convierte un JSON string a un objeto del tipo especificado
     *
     * @param json  String en formato JSON
     * @param clazz Clase del tipo de objeto a deserializar
     * @return Objeto del tipo especificado
     * @throws IOException Si hay error en la conversión
     */
    public <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * Convierte un JSON string a una lista de objetos del tipo especificado
     *
     * @param json  String en formato JSON (array)
     * @param clazz Clase del tipo de objeto a deserializar
     * @return Lista de objetos del tipo especificado
     * @throws IOException Si hay error en la conversión
     */
    public <T> List<T> jsonToList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(
                json,
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
        );
    }
}

