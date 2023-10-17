package com.example.libros.api.controllers;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.application.services.impl.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //Metodos HTTP

    //Metodo GET
    @GetMapping(value = "/authors")
    public ResponseEntity<List<AuthorDto>> getAuthors(){
        List<AuthorDto> authorDtos = authorService.getAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
    }

    //POST
    @PostMapping(value = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto dto){
        //Redirije hacia el responsable de crear un libro
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(dto));
    }

    //PUT
    @PutMapping(value = "/authors/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id ,@RequestBody AuthorDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.updateAuthor(dto, id));
    }

    //Delete
    @DeleteMapping(value = "/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        String result = authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //Busqueda por Id
    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.getAuthorById(id));
    }
}
