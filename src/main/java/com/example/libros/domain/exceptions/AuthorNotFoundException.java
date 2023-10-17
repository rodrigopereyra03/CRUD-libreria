package com.example.libros.domain.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message){
        super(message);

    }
}
