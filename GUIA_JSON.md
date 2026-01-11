# Guía de Uso - Importación de Datos JSON

## Descripción General

Se ha implementado funcionalidad para importar datos desde archivos JSON y para hacer inserciones directas de datos mediante endpoints REST. Se utiliza la librería Jackson para el manejo de JSON.

## Dependencias Agregadas

Se han añadido las siguientes dependencias al `pom.xml`:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

## Clase Utilitaria - JsonUtils

Se ha creado la clase `org.example.utils.JsonUtils` con los siguientes métodos:

- `readJsonFile(String filePath, Class<T> clazz)`: Lee un archivo JSON y lo convierte a una lista de objetos
- `objectToJson(Object object)`: Convierte un objeto a JSON
- `jsonToObject(String json, Class<T> clazz)`: Convierte un JSON string a un objeto
- `jsonToList(String json, Class<T> clazz)`: Convierte un JSON string a una lista

## Métodos en AdestradorService

### 1. Importar desde archivo JSON
```java
public List<Adestrador> importarAdestradoresdesdeFichero(String filePath) throws IOException
```

### 2. Insertar directamente
```java
public Adestrador insertarAdestradorDirecto(String nombre, int idade, String cidade)
```

## Métodos en PokemonService

### 1. Importar desde archivo JSON
```java
public List<Pokemon> importarPokedesdeFichero(String filePath) throws IOException
```

### 2. Insertar directamente
```java
public Pokemon insertarPokemonDirecto(String nombre, String[] tipos, int nivel, String[] habilidades, String adestradorId)
```

## Endpoints REST

### Adestradores

#### Importar desde JSON
```
POST /adestradores/importarDesdeJson?filePath=/ruta/al/archivo.json
```

Ejemplo:
```bash
curl -X POST "http://localhost:8080/adestradores/importarDesdeJson?filePath=/home/figue/MongoPokemon/adestradores.json"
```

#### Insertar directamente
```
POST /adestradores/insertarDirecto?nombre=Ash&idade=10&cidade=Pallet%20Town
```

Ejemplo:
```bash
curl -X POST "http://localhost:8080/adestradores/insertarDirecto?nombre=Ash&idade=10&cidade=Pallet%20Town"
```

### Pokemons

#### Importar desde JSON
```
POST /pokemons/importarDesdeJson?filePath=/ruta/al/archivo.json
```

Ejemplo:
```bash
curl -X POST "http://localhost:8080/pokemons/importarDesdeJson?filePath=/home/figue/MongoPokemon/pokemons.json"
```

#### Insertar directamente
```
POST /pokemons/insertarDirecto?nombre=Pikachu&tipos=Eléctrico&nivel=15&habilidades=Thunderbolt&habilidades=Quick%20Attack&adestradorId=1
```

Ejemplo:
```bash
curl -X POST "http://localhost:8080/pokemons/insertarDirecto?nombre=Pikachu&tipos=Eléctrico&nivel=15&habilidades=Thunderbolt&habilidades=Quick%20Attack&adestradorId=1"
```

## Archivos JSON de Ejemplo

Se incluyen dos archivos JSON de ejemplo en el raíz del proyecto:

### adestradores.json
Contiene una lista de adestradores con sus datos básicos (nombre, edad, ciudad)

### pokemons.json
Contiene una lista de pokemons con sus datos (nombre, tipos, nivel, habilidades, ID del adestrador)

## Estructura del JSON

### Adestradores
```json
[
  {
    "nome": "Ash Ketchum",
    "idade": 10,
    "cidade": "Pallet Town"
  }
]
```

### Pokemons
```json
[
  {
    "nome": "Pikachu",
    "tipos": ["Eléctrico"],
    "nivel": 15,
    "habilidades": ["Thunderbolt", "Quick Attack"],
    "adestradorId": "1"
  }
]
```

## Cambios Realizados

### 1. pom.xml
- Agregadas dependencias de Jackson

### 2. Nuevos archivos
- `src/main/java/org/example/utils/JsonUtils.java`
- `adestradores.json`
- `pokemons.json`

### 3. Archivos modificados
- `src/main/java/org/example/service/AdestradorService.java`: Agregados métodos de importación
- `src/main/java/org/example/service/PokemonService.java`: Agregados métodos de importación
- `src/main/java/org/example/controller/RestAdestrador.java`: Agregados endpoints de importación
- `src/main/java/org/example/controller/RestPokemon.java`: Agregados endpoints de importación

## Notas Importantes

1. Los archivos JSON deben estar en el formato correcto según la estructura de las clases
2. Los IDs en MongoDB se generan automáticamente si no se proporcionan
3. Para pokemons, el `adestradorId` debe referirse a un adestrador existente
4. La librería Jackson maneja automáticamente la conversión de tipos

