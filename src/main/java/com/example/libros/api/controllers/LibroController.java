package com.example.libros.api.controllers;


import com.example.libros.api.dtos.LibroDto;
import com.example.libros.application.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibroController {

    private LibroService service;
    @Autowired
    public LibroController(LibroService service){
        this.service = service;
    }

    //Metodos HTTP
    //Metodo GET
    @GetMapping(value = "/libros")
    public ResponseEntity<List<LibroDto>> getLibros(){
        //1)Obtener todos los libros de la BD
        //Agregar el servicio a la implementacion del metodo del controlador
        List<LibroDto> libros = service.getLibros();

        //2)Devolver la lista y enviar coomo respuesta
        return ResponseEntity.status(HttpStatus.OK).body(libros);
    }
    //GET BY ID
    @GetMapping(value = "/libros/{id}")
    public ResponseEntity<LibroDto> getLibroById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getLibroById(id));
    }
    //POST
    @PostMapping(value = "/libros")
    public ResponseEntity<LibroDto> createLibro(@RequestBody LibroDto dto){
        //Redirije hacia el responsable de crear un libro
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLibro(dto));
    }

    //PUT
    @PutMapping(value = "/libros/{id}")
    public ResponseEntity<LibroDto> updateLibro(@PathVariable Long id,@RequestBody LibroDto libro){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateLibro(id,libro));
    }

    //Delete
    @DeleteMapping(value = "/libros/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id){
        service.deleteLibro(id);
        return ResponseEntity.status(200).body("Se ha eliminado el usuario exitosamente");
    }
}
