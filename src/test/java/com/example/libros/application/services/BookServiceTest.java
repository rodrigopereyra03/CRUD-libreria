package com.example.libros.application.services;

import com.example.libros.api.dtos.BookDto;
import com.example.libros.domain.exceptions.BookNotFoundException;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.IBookRepository;
import com.example.libros.infrastructure.repositories.sql.BookSQLRepository;
import com.example.libros.infrastructure.repositories.sql.IBookSQLRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookSQLRepository repository;
    @InjectMocks
    private BookService service;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        book = new Book();
        book.setName("Rodrigo");
        book.setAuthor("Messi");
        book.setEditionDate(LocalDate.now());
        bookDto = new BookDto();

    }

    @Test
    void getBooks() {
        when(repository.findAll()).thenReturn(Arrays.asList(book));
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
        when(repository.save(any(Book.class))).thenReturn(new Book());
        assertNotNull(service.createBook(bookDto));
    }

    @Test
    void updateBook() {
        bookDto.setId(1L);
        when(repository.existsById(1L)).thenReturn(true);


        when(repository.save(any(Book.class))).thenReturn(book);
        BookDto result = service.updateBook(bookDto);

        assertNotNull(result);

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