package com.example.libros.infrastructure.repositories;

import com.example.libros.domain.models.Book;

import java.util.List;

public interface IBookRepository {

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    List<Book> search(String keyword);

    Boolean existsById(Long id);

    List<Book> findByCode(String code);

    List<Book> findAllById(List<Long> idBooks);
}
