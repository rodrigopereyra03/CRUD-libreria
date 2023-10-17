package com.example.libros.api.mappers;

import com.example.libros.api.dtos.AuthorDto;
import com.example.libros.domain.models.Author;
import com.example.libros.domain.models.Book;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AuthorMapper {

    public Author dtoToAuthor(AuthorDto authorDto){
        Author author = new Author();
        author.setName_author(authorDto.getName_author());
     //   author.setId(authorDto.getId());
        return author;
    }

    public AuthorDto authorToDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        List<Long> idBooks = new ArrayList<>();
        authorDto.setName_author(author.getName_author());
        if (author.getBooks()!=null)
            for (Book b:author.getBooks()) {
                Long id =b.getId();
                idBooks.add(id);
            }
        authorDto.setIdBooks(idBooks);
        authorDto.setId(author.getId());
        return authorDto;
    }
}
