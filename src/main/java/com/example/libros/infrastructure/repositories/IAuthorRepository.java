package com.example.libros.infrastructure.repositories;

import com.example.libros.domain.models.Author;

import java.util.List;

public interface IAuthorRepository {

    List<Author> findAll();

    Author save(Author author);

    Boolean existsById(Long id);

    void deleteById(Long id);

    Author findById(Long id);

    Author getReferenceById(Long id);
}
