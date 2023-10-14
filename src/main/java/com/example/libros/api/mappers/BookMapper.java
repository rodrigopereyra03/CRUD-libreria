package com.example.libros.api.mappers;

import com.example.libros.api.dtos.BookDto;
import com.example.libros.domain.models.Book;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BookMapper {
    //Los mappers me permiten enviar los datos desde una entidad hacia un dto o visceversa

    //Recibe un dto y devuelve un libro
    public Book dtoToBook(BookDto dto){
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setEditionDate(dto.getEditionDate());
        book.setId(dto.getId());

        return book;
    }

    //Recibe un libro y devuelve un dto
    public BookDto bookToDtoMap(Book book){
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setEditionDate(book.getEditionDate());
        dto.setId(book.getId());

        return dto;
    }

    public List<BookDto> toDtoList(List<Book> books) {
        return books.stream()
                .map(BookMapper::bookToDtoMap)
                .toList();
    }

}