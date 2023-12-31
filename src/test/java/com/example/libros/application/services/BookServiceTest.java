package com.example.libros.application.services;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.api.dtos.BookDto;
import com.example.libros.application.services.impl.BookService;
import com.example.libros.domain.exceptions.BookNotFoundException;
import com.example.libros.domain.models.Author;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.sql.AuthorSQLRepository;
import com.example.libros.infrastructure.repositories.sql.BookSQLRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookSQLRepository repository;
    @Mock
    private AuthorSQLRepository authorRepository;
    @InjectMocks
    private BookService service;

    private Book book;
    private BookDto bookDto;

    private AuthorDto authorDto;

    private Author author;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        book = new Book();
        book.setEditionDate(LocalDate.now());
        bookDto = new BookDto();
        authorDto = new AuthorDto();
        author = new Author();
        author.setId(1L);
        service = new BookService(repository,authorRepository);
    }

    @Test
    void getBooks() {
        when(repository.findAll()).thenReturn(Collections.singletonList(book));
        assertNotNull(service.getBooks());
    }

    @Test
    void getBookById() {
        Long id = 1L;
        book.setId(id);
        when(repository.findById(id)).thenReturn(book);
        assertNotNull(service.getBookById(id));
    }

    @Test
    void createBook() {
        bookDto.setAuthorDto(authorDto);
        when(authorRepository.findById(1L)).thenReturn(author);
        when(repository.save(any(Book.class))).thenReturn(book);
        authorDto.setId(1L);
        bookDto.setAuthorDto(authorDto);
        BookDto result = service.createBook(bookDto);

        assertNotNull(result);
    }

    @Test
    void testCreateBookWithInvalidAuthor(){
        when(authorRepository.findById(1L)).thenReturn(null);
        authorDto.setId(1L);
        bookDto.setAuthorDto(authorDto);
        assertThrows(BookNotFoundException.class,()->service.createBook(bookDto));


    }
    //Arreglar
    @Test
    void updateBook() {
        Book existingBook = new Book();
        Mockito.when(repository.findById(1L)).thenReturn(existingBook);
        when(authorRepository.getReferenceById(1L)).thenReturn(author);
        Mockito.when(repository.save(existingBook)).thenReturn(existingBook);
        bookDto.setId(1L);
        bookDto.setCodeBook("1234");
        BookDto result = service.updateBook(bookDto);
        assertEquals("1234",result.getCodeBook());

    }



    @Test
    void testUpdateBookWithNonExistingBook(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);
        assertThrows(BookNotFoundException.class,()->service.updateBook(bookDto));
    }
    @Test
    void deleteBook() {
        Long id =1L;
        when(repository.existsById(id)).thenReturn(true);
        String result = service.deleteBook(id);
        assertEquals("The book has been successfully deleted", result);
        verify(repository,times(1)).deleteById(id);
    }

    @Test
    public void testDeleteBookWhenBookNotExists(){
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(BookNotFoundException.class,()->service.deleteBook(id));


    }

    @Test
    void getBookByNameOrAuthor() {

        String name = "Rodrigo";
        book.setName(name);
        List<Book>books = Arrays.asList(new Book());
        when(repository.search(name)).thenReturn(books);
        List<BookDto> result = service.getBookByNameOrAuthor(name);
        assertNotNull(result);


    }

    @Test
    void getByCode(){
        book.setCodeBook("ABC123");
        when(repository.findByCode("ABC123")).thenReturn(Collections.singletonList(book));
        List<BookDto> result = service.getByCode("ABC123");
        assertEquals(1,result.size());
        assertEquals("ABC123",result.get(0).getCodeBook());
    }
}