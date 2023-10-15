package com.example.libros.api.controllers;


import com.example.libros.api.dtos.BookDto;
import com.example.libros.application.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    //Metodos HTTP

    //Metodo GET
    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDto>> getBooks(){
        //1)Obtener todos los libros de la BD
        List<BookDto> books = service.getBooks();

        //2)Devolver la lista y enviar coomo respuesta
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    //POST
    @PostMapping(value = "/books")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto){
        //Redirije hacia el responsable de crear un libro
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBook(dto));
    }

    //PUT
    @PutMapping(value = "/books")
    public ResponseEntity<BookDto> updateBook( @RequestBody BookDto book){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBook(book));
    }

    //Delete
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        String result = service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //Busqueda por Codigo/Id
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getBookById(id));
    }

    //Busqueda por nombre  parte del nombre o autor
    @GetMapping("/books/getBy")
    public ResponseEntity<List<BookDto>> getBookByNameOrAuthor(@RequestParam String keyword){
        List<BookDto> books = service.getBookByNameOrAuthor(keyword);
        if(!books.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping(value = "/books/getByCode")
    public ResponseEntity<List<BookDto>>getByCode(@RequestParam String code){
        List<BookDto> books = service.getByCode(code);
        if (!books.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
