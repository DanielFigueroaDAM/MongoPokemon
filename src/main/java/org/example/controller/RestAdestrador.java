package org.example.controller;

import org.example.model.Adestrador;
import org.example.service.AdestradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(RestAdestrador.MAPPING)
public class RestAdestrador {
    public static final String MAPPING = "/adestradores";

    @Autowired
    private AdestradorService adestradorService;

    @PostMapping("/guardar")
    public ResponseEntity<Adestrador> guardar(@RequestBody Adestrador adestrador) {
        adestradorService.crearAdestrador(adestrador);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Adestrador>> listarTodos() {
        List <Adestrador> adestradores = adestradorService.buscarAdestradores();
        return new ResponseEntity<>(adestradores,HttpStatus.OK);
    }

    @PostMapping("/importarDesdeJson")
    public ResponseEntity<List<Adestrador>> importarDesdeJson(@RequestParam String filePath) {
        try {
            List<Adestrador> adestradores = adestradorService.importarAdestradoresdesdeFichero(filePath);
            return new ResponseEntity<>(adestradores, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insertarDirecto")
    public ResponseEntity<Adestrador> insertarDirecto(
            @RequestParam String nombre,
            @RequestParam int idade,
            @RequestParam String cidade) {
        Adestrador adestrador = adestradorService.insertarAdestradorDirecto(nombre, idade, cidade);
        return new ResponseEntity<>(adestrador, HttpStatus.CREATED);
    }
}


