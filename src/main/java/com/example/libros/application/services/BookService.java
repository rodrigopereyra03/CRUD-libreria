package com.example.libros.application.services;

import com.example.libros.api.dtos.BookDto;
import com.example.libros.api.mappers.BookMapper;
import com.example.libros.domain.exceptions.BookNotFoundException;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    //Declaro instancia del repositorio
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    // Genero los metodos del CRUD
    public List<BookDto> getBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::bookToDtoMap)
                .toList();

    }
    //Traer Libro por ID
    public BookDto getBookById(Long id){
        return BookMapper.bookToDtoMap(bookRepository.findById(id).get());
    }

    //Crear un libro
    public BookDto createBook(BookDto book){
        return BookMapper.bookToDtoMap(bookRepository.save(BookMapper.dtoToBook(book)));
    }

    public BookDto updateBook(Long id, BookDto book){
        Optional<Book> bookCreated = bookRepository.findById(id);
        if(bookCreated.isPresent()){
            Book entity = bookCreated.get();
            // Actualizo los campos del usuario con los valores del UserDto
            Book bookUpdate = BookMapper.dtoToBook(book);

            bookUpdate.setId(entity.getId());

            Book saved = bookRepository.save(bookUpdate);
            return BookMapper.bookToDtoMap(saved);
        }else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    public String deleteBook(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return "The book has been successfully deleted";
        }else {
            return "The book was not deleted";
        }
    }

    public List<BookDto> getBookByName(String name){
        List<Book> books = bookRepository.search(name);
        return BookMapper.toDtoList(books);
    }


}
