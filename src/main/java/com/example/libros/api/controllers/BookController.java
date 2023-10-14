package com.example.libros.api.controllers;


import com.example.libros.api.dtos.BookDto;
import com.example.libros.application.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private BookService service;
    @Autowired
    public BookController(BookService service){
        this.service = service;
    }

    //Metodos HTTP

    //Metodo GET
    @GetMapping(value = "/books")
    public ResponseEntity<List<BookDto>> getBooks(){
        //1)Obtener todos los libros de la BD
        //Agregar el servicio a la implementacion del metodo del controlador
        List<BookDto> books = service.getBooks();

        //2)Devolver la lista y enviar coomo respuesta
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
    //GET BY ID

    //POST
    @PostMapping(value = "/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto dto){
        //Redirije hacia el responsable de crear un libro
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBook(dto));
    }

    //PUT
    @PutMapping(value = "/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto book){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateBook(id,book));
    }

    //Delete
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("The book has been successfully deleted");
    }

    //EndPoints

    //Busqueda por Codigo/Id
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getBookById(id));
    }

    //Busqueda por nombre o parte del nombre
    @GetMapping("/books/getByName")
    public ResponseEntity<List<BookDto>> getBookByNameOrAuthor(@RequestParam String keyword){
        List<BookDto> books = service.getBookByName(keyword);
        if(!books.isEmpty()){
            return new ResponseEntity<>(books,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
