package com.example.libros.application.services;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.application.services.impl.AuthorService;
import com.example.libros.domain.exceptions.AuthorNotFoundException;
import com.example.libros.domain.models.Author;
import com.example.libros.infrastructure.repositories.IAuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceTest {


    @Mock
    private IAuthorRepository repository;

    @InjectMocks
    private AuthorService service;
    private Author author;
    private AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        author = new Author();
        author.setName_author("Rodrigo");
        authorDto = new AuthorDto();
    }

    @Test
    void getAuthors() {
        when(repository.findAll()).thenReturn(Arrays.asList(author));
        assertNotNull(service.getAuthors());
    }

    @Test
    void getAuthorById() {
        Long id = 1L;
        author.setId(id);
        when(repository.findById(id)).thenReturn(author);
        assertNotNull(service.getAuthorById(id));
    }

    @Test
    void createAuthor() {
        when(repository.save(any(Author.class))).thenReturn(new Author());
        assertNotNull(service.createAuthor(authorDto));
    }

   /* @Test
    void updateAuthor() {
        Long id = 1L;
        authorDto.setIdBooks(List.of(2L,3L));

        author.setId(id);
        List<Book> books = new java.util.ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        author.setBooks(books);

        when(repository.findById(id)).thenReturn(author);
        when(repository.save(any(Author.class))).thenReturn(author);
        AuthorDto result = service.updateAuthor(authorDto);

        assertNotNull(result);
    }*/

    @Test
    void testUpdateAuthorWithNonExistingAuthor(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);
        assertThrows(AuthorNotFoundException.class,()->service.updateAuthor(authorDto,id));
    }
    @Test
    void deleteAuthor() {
        Long id =1L;
        when(repository.existsById(id)).thenReturn(true);
        String result = service.deleteAuthor(id);
        assertEquals("The Author has been successfully deleted", result);
        verify(repository,times(1)).deleteById(id);
    }
    @Test
    public void testDeleteAuthorWhenAuthorNotExists(){
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(AuthorNotFoundException.class,()->service.deleteAuthor(id));


    }


}