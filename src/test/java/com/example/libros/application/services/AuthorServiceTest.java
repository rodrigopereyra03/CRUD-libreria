package com.example.libros.application.services;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.api.mappers.AuthorMapper;
import com.example.libros.application.services.impl.AuthorService;
import com.example.libros.domain.exceptions.AuthorNotFoundException;
import com.example.libros.domain.models.Author;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.IAuthorRepository;
import com.example.libros.infrastructure.repositories.IBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceTest {


    @Mock
    private IAuthorRepository repository;
    @Mock
    private IBookRepository bookRepository;

    @InjectMocks
    private AuthorService service;
    private Author author;
    private AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        author = new Author();

        authorDto = new AuthorDto();
    }

    @Test
    void testGetAuthors() {
        Author author1 = new Author("Author1");
        Author author2 = new Author("Author2");
        when(repository.findAll()).thenReturn(Arrays.asList(author1,author2));
        List<AuthorDto> result = service.getAuthors();
        assertEquals(2,result.size());

        assertEquals("Author1",result.get(0).getName_author());
        assertEquals("Author2",result.get(1).getName_author());
    }

    @Test
    void testGetAuthorById() {
        Long id = 1L;
        author.setId(id);
        when(repository.findById(id)).thenReturn(author);
        AuthorDto result =service.getAuthorById(id);
        assertNotNull(result);
    }

    @Test
    void testCreateAuthor() {
        when(repository.save(any(Author.class))).thenReturn(new Author());
        assertNotNull(service.createAuthor(authorDto));
    }

    @Test
    void updateAuthor() {
        Long id = 1L;
        Book book = new Book(1L,"Libro1");
        Book book1 = new Book(2L,"Libro2");
        author.setId(id);
        author.setName_author("nombre");
        author.setBooks(Arrays.asList(book,book1));
        when(repository.findById(id)).thenReturn(author);


        Mockito.when(bookRepository.findAllById(Arrays.asList(1L,2L))).thenReturn(Arrays.asList(book, book1));

        AuthorDto updateAuthor = new AuthorDto();
        updateAuthor.setId(id);
        updateAuthor.setName_author("nuevo nombre");
        updateAuthor.setIdBooks(Arrays.asList(1L,2L));

        when(repository.save(any())).thenReturn(AuthorMapper.dtoToAuthor(updateAuthor));

        AuthorDto result = service.updateAuthor(1L,updateAuthor);
        assertNotNull(result);
        assertEquals("nuevo nombre",result.getName_author());

    }

    @Test
    void testUpdateAuthorWithNonExistingAuthor(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);
        assertThrows(AuthorNotFoundException.class,()->service.updateAuthor(id,authorDto));
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