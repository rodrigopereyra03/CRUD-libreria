package com.example.libros.infrastructure.repositories.sql;

import com.example.libros.domain.exceptions.AuthorNotFoundException;

import com.example.libros.domain.models.Author;

import com.example.libros.infrastructure.repositories.IAuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorSQLRepository implements IAuthorRepository {
    private final IAuthorSQLRepository authorSQLRepository;

    public AuthorSQLRepository(IAuthorSQLRepository authorSQLRepository) {
        this.authorSQLRepository = authorSQLRepository;
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> author = authorSQLRepository.findById(id);
        if(author.isEmpty()){
            throw new AuthorNotFoundException("Author not found with id: " + id);

        }
        return author.get();
    }

    @Override
    public Author getReferenceById(Long id) {
        return authorSQLRepository.getReferenceById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorSQLRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        return authorSQLRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorSQLRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return authorSQLRepository.existsById(id);
    }

}
