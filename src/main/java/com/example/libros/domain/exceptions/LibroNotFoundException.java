package com.example.libros.domain.exceptions;



public class LibroNotFoundException extends RuntimeException{
    public LibroNotFoundException(String message){
        super(message);
    }
}
