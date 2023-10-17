package com.example.libros.application.services.impl;

import com.example.libros.api.dtos.BookDto;
import com.example.libros.api.mappers.BookMapper;
import com.example.libros.application.services.IBookService;
import com.example.libros.domain.exceptions.BookNotFoundException;
import com.example.libros.domain.models.Author;
import com.example.libros.domain.models.Book;
import com.example.libros.infrastructure.repositories.IAuthorRepository;
import com.example.libros.infrastructure.repositories.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final IAuthorRepository iAuthorRepository;

    public BookService(IBookRepository bookRepository, IAuthorRepository iAuthorRepository) {
        this.bookRepository = bookRepository;
        this.iAuthorRepository = iAuthorRepository;
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
        return BookMapper.bookToDtoMap(bookRepository.findById(id));
    }

    //Crear un libro
    public BookDto createBook(BookDto book){
        Optional<Author> author = Optional.ofNullable(iAuthorRepository.findById(book.getAuthorDto().getId()));
        Book bookModel = BookMapper.dtoToBook(book);
        bookModel.setAuthor(author.get()); //chequear q exista y si no existe tirar un error
        bookModel = bookRepository.save(bookModel);
        BookDto dto = BookMapper.bookToDtoMap(bookModel);
        return dto;


       // return BookMapper.bookToDtoMap(bookRepository.save(BookMapper.dtoToBook(book)));
    }

    public BookDto updateBook(BookDto book){
        Boolean exists = bookRepository.existsById(book.getId());
        if(!exists){
            throw new BookNotFoundException("Book not found with id: " + book.getId());
        }
        Book updated = bookRepository.save(BookMapper.dtoToBook(book));

        return BookMapper.bookToDtoMap(updated);
    }

    public String deleteBook(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return "The book has been successfully deleted";
        }else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    public List<BookDto> getBookByNameOrAuthor(String keyword){
        List<Book> books = bookRepository.search(keyword.toLowerCase());
        return BookMapper.toDtoList(books);
    }

    public List<BookDto> getByCode(String code){
        List<Book> books = bookRepository.findByCode(code);
        return BookMapper.toDtoList(books);
    }

}
