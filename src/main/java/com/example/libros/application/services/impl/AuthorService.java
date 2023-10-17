package com.example.libros.application.services.impl;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.api.mappers.AuthorMapper;
import com.example.libros.domain.exceptions.AuthorNotFoundException;
import com.example.libros.domain.models.Author;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.IAuthorRepository;
import com.example.libros.infrastructure.repositories.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final IAuthorRepository repository;
    private final IBookRepository iBookRepository;

    public AuthorService(IAuthorRepository repository, IBookRepository iBookRepository) {
        this.repository = repository;
        this.iBookRepository = iBookRepository;
    }

    // Genero los metodos del CRUD

    //GET
    public List<AuthorDto> getAuthors(){
        List<Author> author = repository.findAll();
        return author.stream()
                .map(AuthorMapper::authorToDto)
                .toList();
    }

    //Traer Autor por ID
    public AuthorDto getAuthorById(Long id){
        return AuthorMapper.authorToDto(repository.findById(id));
    }

    //Crear un Autor
    public AuthorDto createAuthor(AuthorDto authorDto){
        return AuthorMapper.authorToDto(repository.save(AuthorMapper.dtoToAuthor(authorDto)));
    }

    public AuthorDto updateAuthor(AuthorDto authorDto, Long id){
        Optional<Author> created = Optional.ofNullable(repository.findById(id));
        if (created.isPresent()){
            Author entity =created.get();
            Author bookUpdate = AuthorMapper.dtoToAuthor(authorDto);
            bookUpdate.setBooks(entity.getBooks());
            if (authorDto.getIdBooks()!=null){
                List<Book> bookList = iBookRepository.findAllById(authorDto.getIdBooks());
                List<Book> bookListFilter = bookList.stream().filter(e->!entity.getBooks().contains(e)).toList();
                bookUpdate.getBooks().addAll(bookListFilter);
                bookUpdate.setBooks(bookList);
            }
            bookUpdate.setId(entity.getId());

            Author saved = repository.save(bookUpdate);

            return AuthorMapper.authorToDto(saved);
        }else {
            throw new AuthorNotFoundException("Author not found exceptions with id: "+ id);
        }
    }


    //Actualizo un Autor
    /*public AuthorDto updateAuthor(AuthorDto authorDto){
        Boolean exists = repository.existsById(authorDto.getId());
        if(!exists){
            throw new AuthorNotFoundException("Author not found with id: " + authorDto.getId());
        }
        Author updated = repository.save(AuthorMapper.dtoToAuthor(authorDto));

        return AuthorMapper.authorToDto(updated);
    }*/

    //Elimino un Autor por id
    public String deleteAuthor(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "The Author has been successfully deleted";
        }else {
            throw new AuthorNotFoundException("Author not found with id: " + id);
        }
    }

}
