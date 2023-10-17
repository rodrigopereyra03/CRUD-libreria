package com.example.libros.application.services;

import com.example.libros.api.dtos.BookDto;

import java.util.List;

public interface IBookService {

    public List<BookDto> getBooks();

    public BookDto getBookById(Long id);

    public BookDto createBook(BookDto book);

    public BookDto updateBook(BookDto book);

    public String deleteBook(Long id);

    public List<BookDto> getBookByNameOrAuthor(String keyword);

    public List<BookDto> getByCode(String code);



}
